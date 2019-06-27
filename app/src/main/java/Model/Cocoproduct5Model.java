package Model;


/**
 * Created by wolfsoft4 on 8/12/18.
 */

public class Cocoproduct5Model {

    Integer mobile,star;
    String modelname,txtrating,rating;


    public Integer getMobile() {
        return mobile;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getModelname() {
        return modelname;
    }

    public void setModelname(String modelname) {
        this.modelname = modelname;
    }

    public String getTxtrating() {
        return txtrating;
    }

    public void setTxtrating(String txtrating) {
        this.txtrating = txtrating;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Cocoproduct5Model(Integer mobile, Integer star, String modelname, String txtrating, String rating) {
        this.mobile = mobile;
        this.star = star;
        this.modelname = modelname;
        this.txtrating = txtrating;
        this.rating = rating;
    }
}
