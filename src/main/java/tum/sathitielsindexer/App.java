package tum.sathitielsindexer;

import org.springframework.context.ApplicationContext;

import org.joda.time.DateTime;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tum.elasticsearch.Indexer;
import tum.sathiti.dao.ClickDao;
import tum.sathiti.model.PageviewStatisticsModel;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/app-context.xml");
        ClickDao clickDao = context.getBean("clickDao", ClickDao.class);
        List<PageviewStatisticsModel> pageviewStatisticsModels = clickDao.queryClickStatistics(DateTime.now().minusMonths(1), DateTime.now());
        System.out.print(pageviewStatisticsModels.size());

        Indexer indexer = new Indexer();
        //indexer.index(pageviewStatisticsModels);
    }
}
