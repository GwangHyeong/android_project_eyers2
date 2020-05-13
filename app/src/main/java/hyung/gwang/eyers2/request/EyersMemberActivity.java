package hyung.gwang.eyers2.request;

public class EyersMemberActivity { // 자바 빈 (java Bean)
    public int img; // 사진 - res/drawable
    public String name = "";
    public String skill ="";
    public String homepage = "";
    public String email = "";
    boolean isDomestic;
    String shipDate = "";

    // 생성자가 있으면 객체 생성시 편리하다
    public EyersMemberActivity(int img, String name , String skill, String homepage, String email) {
        this.img = img;
        this.name = name;
        this.skill = skill;
        this.homepage = homepage;
        this.email = email;


    }
    public EyersMemberActivity() {}// 기존 코드와 호환을 위해서 생성자 작업시 기본생성자도 추가
}
