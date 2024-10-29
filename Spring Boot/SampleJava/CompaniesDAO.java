public class CompaniesDAO {

    private String name;
    private String region;
    private Integer month_1;
    private Integer month_2;
    private Integer month_3;
    private Integer month_4;
    private Integer month_5;
    private Integer month_6;
    private Integer month_7;
    private Integer month_8;
    private Integer month_9;
    private Integer month_10;
    private Integer month_11;
    private Integer month_12;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getMonth_1() {
        return month_1;
    }

    public void setMonth_1(Integer month_1) {
        this.month_1 = month_1;
    }

    public Integer getMonth_2() {
        return month_2;
    }

    public void setMonth_2(Integer month_2) {
        this.month_2 = month_2;
    }

    public Integer getMonth_3() {
        return month_3;
    }

    public void setMonth_3(Integer month_3) {
        this.month_3 = month_3;
    }

    public Integer getMonth_4() {
        return month_4;
    }

    public void setMonth_4(Integer month_4) {
        this.month_4 = month_4;
    }

    public Integer getMonth_5() {
        return month_5;
    }

    public void setMonth_5(Integer month_5) {
        this.month_5 = month_5;
    }

    public Integer getMonth_6() {
        return month_6;
    }

    public void setMonth_6(Integer month_6) {
        this.month_6 = month_6;
    }

    public Integer getMonth_7() {
        return month_7;
    }

    public void setMonth_7(Integer month_7) {
        this.month_7 = month_7;
    }

    public Integer getMonth_8() {
        return month_8;
    }

    public void setMonth_8(Integer month_8) {
        this.month_8 = month_8;
    }

    public Integer getMonth_9() {
        return month_9;
    }

    public void setMonth_9(Integer month_9) {
        this.month_9 = month_9;
    }

    public Integer getMonth_10() {
        return month_10;
    }

    public void setMonth_10(Integer month_10) {
        this.month_10 = month_10;
    }

    public Integer getMonth_11() {
        return month_11;
    }

    public void setMonth_11(Integer month_11) {
        this.month_11 = month_11;
    }

    public Integer getMonth_12() {
        return month_12;
    }

    public void setMonth_12(Integer month_12) {
        this.month_12 = month_12;
    }

    public CompaniesDAO(String name, String region, Integer month_1, Integer month_2, Integer month_3, Integer month_4, Integer month_5, Integer month_6, Integer month_7, Integer month_8, Integer month_9, Integer month_10, Integer month_11, Integer month_12) {
        this.name = name;
        this.region = region;
        this.month_1 = month_1;
        this.month_2 = month_2;
        this.month_3 = month_3;
        this.month_4 = month_4;
        this.month_5 = month_5;
        this.month_6 = month_6;
        this.month_7 = month_7;
        this.month_8 = month_8;
        this.month_9 = month_9;
        this.month_10 = month_10;
        this.month_11 = month_11;
        this.month_12 = month_12;
    }

    public CompaniesDAO() {
    }

    @Override
    public String toString() {
        return "CompaniesDAO{" +
                "name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", month_1=" + month_1 +
                ", month_2=" + month_2 +
                ", month_3=" + month_3 +
                ", month_4=" + month_4 +
                ", month_5=" + month_5 +
                ", month_6=" + month_6 +
                ", month_7=" + month_7 +
                ", month_8=" + month_8 +
                ", month_9=" + month_9 +
                ", month_10=" + month_10 +
                ", month_11=" + month_11 +
                ", month_12=" + month_12 +
                '}';
    }
}
