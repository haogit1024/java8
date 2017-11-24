### 收集器接口
![Alt collector_interface](image/collector_interface.png)  
本列表适用以下定义
* T是流中要收集的项目的泛型。
* A是累加器的类型，累加器是在收集过程中用于累积部分结果的对象。
* R是收集操作得到的对象（通常但并不一定是集合）的类型。

#### 1.建立新的结果容器: supplier方法
supplier方法必须返回一个结果为空的Supplier，也就是一个无参数函数，在调用时它会
创建一个空的累加器实例，供数据收集过程使用。很明显，对于将累加器本身作为结果返回的收
集器，比如我们的ToListCollector，在对空流执行操作的时候，这个空的累加器也代表了收
集过程的结果。在我们的ToListCollector中， supplier返回一个空的List，如下所示：  
>public Supplier<List<T>> supplier() {
return () -> new ArrayList<T>();
}  

请注意你也可以只传递一个构造函数引用：  
>public Supplier<List<T>> supplier() {
return ArrayList::new;
}

#### 2.将元素添加到结果容器: accumulator方法
accumulator方法会返回执行归约操作的函数。当遍历到流中第n个元素时，这个函数执行
时会有两个参数：保存归约结果的累加器（已收集了流中的前 n-1 个项目）， 还有第n个元素本身。
该函数将返回void，因为累加器是原位更新，即函数的执行改变了它的内部状态以体现遍历的
元素的效果。对于ToListCollector，这个函数仅仅会把当前项目添加至已经遍历过的项目的
列表：  
>public BiConsumer<List<T>, T> accumulator() {
return (list, item) -> list.add(item);
}  

你也可以使用方法引用，这会更为简洁：
>public BiConsumer<List<T>, T> accumulator() {
return List::add;
}

#### 3.对结果容器应用最终转换: finisher方法  
在遍历完流后， finisher方法必须返回在累积过程的最后要调用的一个函数，以便将累加
器对象转换为整个集合操作的最终结果。通常，就像ToListCollector的情况一样，累加器对
象恰好符合预期的最终结果，因此无需进行转换。所以finisher方法只需返回identity函数：
>public Function<List<T>, List<T>> finisher() {
return Function.identity();
}

这三个方法已经足以对流进行顺序归约，至少从逻辑上看可以按图6-7进行。实践中的实现
细节可能还要复杂一点，一方面是因为流的延迟性质，可能在collect操作之前还需要完成其他
中间操作的流水线，另一方面则是理论上可能要进行并行归约。  
![Alt collector_implement_flow](image/collectors_implement_flow.PNG)  

#### 4.合并两个结果容器: combiner方法
四个方法中的最后一个——combiner方法会返回一个供归约操作使用的函数，它定义了对
流的各个子部分进行并行处理时，各个子部分归约所得的累加器要如何合并。对于toList而言，
这个方法的实现非常简单，只要把从流的第二个部分收集到的项目列表加到遍历第一部分时得到
的列表后面就行了：  
>public BinaryOperator<List<T>> combiner() {
return (list1, list2) -> {
list1.addAll(list2);
return list1; }
}  

有了这第四个方法，就可以对流进行并行归约了。它会用到Java 7中引入的分支/合并框架和
Spliterator抽象，我们会在下一章中讲到。这个过程类似于图6-8所示，这里会详细介绍  
* 原始流会以递归方式拆分为子流，直到定义流是否需要进一步拆分的一个条件为非（如
果分布式工作单位太小，并行计算往往比顺序计算要慢，而且要是生成的并行任务比处
理器内核数多很多的话就毫无意义了）。
* 现在，所有的子流都可以并行处理，即对每个子流应用图6-7所示的顺序归约算法。
* 最后，使用收集器combiner方法返回的函数，将所有的部分结果两两合并。这时会把原
始流每次拆分时得到的子流对应的结果合并起来。  
![Alt combiner_parallelism](image/combiner_parallelism.PNG)  

#### 5.characteristics方法
最后一个方法——characteristics会返回一个不可变的Characteristics集合，它定义
了收集器的行为——尤其是关于流是否可以并行归约，以及可以使用哪些优化的提示。
Characteristics是一个包含三个项目的枚举。
* UNORDERED——归约结果不受流中项目的遍历和累积顺序的影响。
* CONCURRENT——accumulator函数可以从多个线程同时调用，且该收集器可以并行归
约流。如果收集器没有标为UNORDERED，那它仅在用于无序数据源时才可以并行归约。
* IDENTITY_FINISH——这表明完成器方法返回的函数是一个恒等函数，可以跳过。这种
情况下，累加器对象将会直接用作归约过程的最终结果。这也意味着，将累加器A不加检
查地转换为结果R是安全的。  
我们迄今开发的ToListCollector是IDENTITY_FINISH的，因为用来累积流中元素的
List已经是我们要的最终结果，用不着进一步转换了，但它并不是UNORDERED，因为用在有序
流上的时候，我们还是希望顺序能够保留在得到的List中。最后，它是CONCURRENT的，但我们
刚才说过了，仅仅在背后的数据源无序时才会并行处理。  
