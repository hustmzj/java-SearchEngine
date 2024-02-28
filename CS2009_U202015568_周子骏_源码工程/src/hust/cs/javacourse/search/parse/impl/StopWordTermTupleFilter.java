package hust.cs.javacourse.search.parse.impl;

import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleFilter;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;
import hust.cs.javacourse.search.util.StopWords;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StopWordTermTupleFilter extends AbstractTermTupleFilter {

    private List<String> stopWord;

    /**
     * 构造函数
     *
     * @param input : 输入流
     */
    public StopWordTermTupleFilter(AbstractTermTupleStream input) {
        super(input);
        this.stopWord = new ArrayList<String>(Arrays.asList(StopWords.STOP_WORDS));
    }

    /**
     * 获得下一个三元组
     * 过滤掉停等词
     *
     * @return: 下一个三元组；如果到了流的末尾，返回null
     */
    @Override
    public AbstractTermTuple next() {
        AbstractTermTuple stopWordFilter = input.next();    //调用上一层的过滤机制
        if (stopWordFilter == null) return null;
        while (stopWord.contains(stopWordFilter.term.getContent())) {       //如果不合要求，返回上级重新取一个三元组，再进行层层过滤
            stopWordFilter = input.next();
            if (stopWordFilter == null) return null;
        }
        return stopWordFilter;
    }



}
