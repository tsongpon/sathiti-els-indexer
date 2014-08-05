package tum.sathiti.dao;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import tum.sathiti.model.PageviewStatisticsModel;

import java.util.List;

/**
 *
 */

@Repository
public class PageviewDao {

    @Autowired(required = true)
    private MongoOperations mongoOperations;

    public List<PageviewStatisticsModel> query(DateTime from, DateTime to) {

        Query query = new Query();
        query.addCriteria(Criteria.where("statisticsDate").gte(from.withTimeAtStartOfDay()).lte(to.withTimeAtStartOfDay()));
        return mongoOperations.find(query, PageviewStatisticsModel.class);
    }
}
