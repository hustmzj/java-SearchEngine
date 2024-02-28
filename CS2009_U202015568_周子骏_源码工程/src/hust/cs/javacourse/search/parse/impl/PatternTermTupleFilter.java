package hust.cs.javacourse.search.parse.impl;

import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleFilter;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;
import hust.cs.javacourse.search.util.Config;

public class PatternTermTupleFilter extends AbstractTermTupleFilter {

    public PatternTermTupleFilter(AbstractTermTupleStream input) {
        super(input);
    }

    @Override
    public AbstractTermTuple next() {
        AbstractTermTuple tuple = input.next();  //调用上一层的过滤机制
        if (tuple == null) return null;
        while (!tuple.term.getContent().matches(Config.TERM_FILTER_PATTERN)) {
            tuple = input.next();           //如果不合要求，返回上级重新取一个三元组，再进行层层过滤
            if (tuple == null) return null;
        }
        return tuple;
    }
}
