package tum.sathiti.model;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 */
@Document(collection = "clickstatistic")
public class ClickStatisticModel {
    @Id
    private String id;
    private String siteCode;
    private Long companyId;
    private Long prospectId;
    private DeviceType device;
    private DateTime statisticsDate;
    private String prospectTitle;
    private String address;
    private String postCode;
    private String postArea;
    private Integer statisticsCount;
    private String clickArea;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getProspectId() {
        return prospectId;
    }

    public void setProspectId(Long prospectId) {
        this.prospectId = prospectId;
    }

    public DeviceType getDevice() {
        return device;
    }

    public void setDevice(DeviceType device) {
        this.device = device;
    }

    public DateTime getStatisticsDate() {
        return statisticsDate;
    }

    public void setStatisticsDate(DateTime statisticsDate) {
        this.statisticsDate = statisticsDate;
    }

    public String getProspectTitle() {
        return prospectTitle;
    }

    public void setProspectTitle(String prospectTitle) {
        this.prospectTitle = prospectTitle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPostArea() {
        return postArea;
    }

    public void setPostArea(String postArea) {
        this.postArea = postArea;
    }

    public Integer getStatisticsCount() {
        return statisticsCount;
    }

    public void setStatisticsCount(Integer statisticsCount) {
        this.statisticsCount = statisticsCount;
    }

    public String getClickArea() {
        return clickArea;
    }

    public void setClickArea(String clickArea) {
        this.clickArea = clickArea;
    }
}
