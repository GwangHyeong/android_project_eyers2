package hyung.gwang.eyers2.request;

public class MemberTestRequest {
    String name;
    String skill;
    String homepage;
    String email;



    String img;
    int seq;
    public MemberTestRequest(String img,String name, String skill, String homepage,String email, int seq){
        this.img = img;
        this.name = name;
        this.skill = skill;
        this.homepage = homepage;
        this.email = email;
        this.seq = seq;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    public int getSeq(){
        return seq;
    }
    public void setSeq(int seq){
        this.seq = seq;
    }
}
