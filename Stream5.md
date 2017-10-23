## 原始类型流
Java 8引入了三个原始类型特化流接口来装箱成本问题: IntStream 、 DoubleStream 和LongStream ,分别将流中的元素特化为 int 、 long 和 double ,从而避免了暗含的装箱成本  
#### 对象流转数值流(特化流)
将流转换为特化版本的常用方法是 mapToInt 、 mapToDouble 和 mapToLong 。这些方法和前面说的 map 方法的工作方式一样,只是它们返回的是一个特化流(数值流),而不是 Stream<T>  

#### 转换会对象流
要 把 原 始 流 转 换 成 一 般 流 ( 每 个 int 都 会 装 箱 成 一 个Integer ),可以使用 boxed 方法  

代码Stream5_6_1演示了这两种操作
#### 创建流
通过值,数组,文件,函数创建流,代码为Stream5_7
>Stream API提供了两个静态方法来从函数生成流: Stream.iterate 和 Stream.generate 。
这两个操作可以创建所谓的无限流:不像从固定集合创建的流那样有固定大小的流。由 iterate
和 generate 产生的流会用给定的函数按需创建值,因此可以无穷无尽地计算下去!一般来说,
应该使用 limit(n) 来对这种流加以限制,以避免打印无穷多个值。  

iterate产生的流是顺序的,无界的,因为值是按需计算的,可以永远计算下去,这是流和集合之间的一个关键区别
与iterate方法类似， generate方法也可让你按需生成一个无限流。但generate不是依次对每个新生成的值应用函数的。它接受一个Supplier<T>类型的Lambda提供新的值  

#### 5.8小结
这一章很长，但是很有收获！现在你可以更高效地处理集合了。事实上，流让你可以简洁地表达复杂的数据处理查询。此外，流可以透明地并行化。以下是你应从本章中学到的关键概念。
* Streams API可以表达复杂的数据处理查询。常用的流操作总结在表5-1中。
* 你可以使用filter、 distinct、 skip和limit对流做筛选和切片。
* 你可以使用map和flatMap提取或转换流中的元素。
* 你可以使用findFirst和findAny方法查找流中的元素。你可以用allMatch、noneMatch和anyMatch方法让流匹配给定的谓词。
* 这些方法都利用了短路：找到结果就立即停止计算；没有必要处理整个流。
* 你可以利用reduce方法将流中所有的元素迭代合并成一个结果，例如求和或查找最大元素。
* filter和map等操作是无状态的，它们并不存储任何状态。 reduce等操作要存储状态才能计算出一个值。 sorted和distinct等操作也要存储状态，因为它们需要把流中的所有元素缓存起来才能返回一个新的流。这种操作称为有状态操作。
* 流有三种基本的原始类型特化： IntStream、 DoubleStream和LongStream。它们的操作也有相应的特化。
* 流不仅可以从集合创建，也可从值、数组、文件以及iterate与generate等特定方法创建。
* 无限流是没有固定大小的流。
