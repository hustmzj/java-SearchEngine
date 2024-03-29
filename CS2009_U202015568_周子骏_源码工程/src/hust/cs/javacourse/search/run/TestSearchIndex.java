/**
 * author: garfield
 * made on 2021/4/22
 */
package hust.cs.javacourse.search.run;

import hust.cs.javacourse.search.index.AbstractTerm;
import hust.cs.javacourse.search.index.impl.Term;
import hust.cs.javacourse.search.query.AbstractHit;
import hust.cs.javacourse.search.query.AbstractIndexSearcher;
import hust.cs.javacourse.search.query.impl.IndexSearcher;
import hust.cs.javacourse.search.query.impl.SimpleSorter;
import hust.cs.javacourse.search.util.Config;

/**
 * 测试搜索
 */
public class TestSearchIndex {
    /**
     * 搜索程序入口
     *
     * @param args ：命令行参数
     */
    public static void main(String[] args) {
        AbstractIndexSearcher searcher = new IndexSearcher();
        searcher.open(Config.INDEX_DIR + "index.dat");
        AbstractTerm queryTerm1 = new Term("health".toLowerCase());
        AbstractTerm queryTerm2 = new Term("people".toLowerCase());
        AbstractHit[] hits = searcher.search(queryTerm1, new SimpleSorter());
        if (hits!=null){
            for (AbstractHit hit:hits
            ) {
                System.out.println(hit.getDocId()+":"+hit.getScore());
            }
        }

      AbstractHit[] hits_ = searcher.search(queryTerm1, queryTerm2, new SimpleSorter(), AbstractIndexSearcher.LogicalCombination.OR);
      System.out.println("======================================");
      if (hits_.length != 0) {
          for (AbstractHit hit : hits_) {
              System.out.println(hit);
              System.out.println("======================================");
          }
      } else {
          System.out.println("Not Found");
      }

    }
}
