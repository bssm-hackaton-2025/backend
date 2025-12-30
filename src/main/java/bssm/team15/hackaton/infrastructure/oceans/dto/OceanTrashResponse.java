package bssm.team15.hackaton.infrastructure.oceans.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class OceanTrashResponse {

    @XmlElement(name = "header")
    private Header header;

    @XmlElement(name = "body")
    private Body body;

    @Getter
    @Setter
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Header {
        @XmlElement(name = "resultCode")
        private String resultCode;

        @XmlElement(name = "resultMsg")
        private String resultMsg;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Body {
        @XmlElement(name = "items")
        private Items items;

        @XmlElement(name = "numOfRows")
        private int numOfRows;

        @XmlElement(name = "pageNo")
        private int pageNo;

        @XmlElement(name = "totalCount")
        private int totalCount;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Items {
        @XmlElement(name = "item")
        private List<Item> itemList;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Item {
        @XmlElement(name = "monitoringyear")
        private String monitoringYear;

        @XmlElement(name = "monitoringchasu")
        private Integer monitoringChasu;

        @XmlElement(name = "monitoringzonename")
        private String monitoringZoneName;

        @XmlElement(name = "monitoringdate")
        private String monitoringDate;

        @XmlElement(name = "monitoringpeoplenum")
        private Integer monitoringPeoplenum;

        @XmlElement(name = "monitoringea")
        private Integer monitoringEa;

        @XmlElement(name = "monitoringkg")
        private Double monitoringKg;

        @XmlElement(name = "monitoringl")
        private Double monitoringL;
    }
}
