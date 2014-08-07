package tum.sathitielsindexer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import org.joda.time.DateTime;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tum.elasticsearch.Indexer;
import tum.sathiti.dao.ClickDao;
import tum.sathiti.dao.PageviewDao;
import tum.sathiti.model.ClickStatisticModel;
import tum.sathiti.model.PageviewStatisticsModel;

import java.util.List;

/**
 *
 */
public class App
{
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main( String[] args ) throws Exception{
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/app-context.xml");

        PageviewDao pageviewDao = context.getBean("pageviewDao", PageviewDao.class);
        List<PageviewStatisticsModel> pageviewStatisticsModels = pageviewDao.query(DateTime.now().minusYears(1), DateTime.now());
        LOG.debug("Got PageviewStatistic size : {}", pageviewStatisticsModels.size());

        ClickDao clickDao = context.getBean("clickDao", ClickDao.class);
        List<ClickStatisticModel> clickStatisticModels = clickDao.query(DateTime.now().minusYears(1), DateTime.now());
        LOG.debug("Got ClickStatistic size : {}", clickStatisticModels.size());

        LOG.debug("Starting index sathiti data");
        Indexer indexer = new Indexer();
        indexer.indexPageview(pageviewStatisticsModels);
        indexer.indexClick(clickStatisticModels);
        LOG.debug("Finished index sathiti data");
    }
}
