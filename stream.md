### 使用流笔记
#### optional简介
Optional<T> 类( java.util.Optional )是一个容器类,代表一个值存在或不存在。  
Optional 里面几种可以迫使你显式地检查值是否存在或处理值不存在的情形。  
1. isPresent() 将在 Optional 包含值的时候返回 true , 否则返回 false 。  
2. ifPresent(Consumer<T> block) 会在值存在的时候执行给定的代码块(执行实现Consumer接口的代码)。   
3. T get() 会在值存在时返回值,否则抛出一个 NoSuchElement 异常。  
4. T orElse(T other) 会在值存在时返回值,否则返回一个默认值。  

findFirst找到第一个元素在并行上限制更多。如果你不关心返回的元素是哪个,请使用 findAny ,因为它在使用并行流时限制较少.
