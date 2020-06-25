package hyung.gwang.eyers2.request;
//리스트 뷰에 뿌려주는 데이터를 담기 위한 클래스.
public class NoticeRequest {
        String notice;
        String name;
        String date;
        int seq;
public NoticeRequest(String notice, String name, String date, int seq){
        this.notice = notice;
        this.name = name;
        this.date = date;
        this.seq = seq;
        }
public String getNotice(){
        return notice;
        }
public void setNotice(String notice){
        this.notice = notice;
        }
public String getName(){
        return name;
        }
public void setName(String name){
        this.name = name;
        }
public String getDate(){
        return date;
        }
public void setDate(String date){
        this.date = date;
        }
public int getSeq(){
        return seq;
        }
public void setSeq(int seq){
        this.seq = seq;
        }
        }
