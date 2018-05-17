package model;

public class YoungChild extends User{
	private String parent_id1;
    private String parent_id2;

    public YoungChild(String name, int age, String state, String gender,String parent_id1, String parent_id2) {
 		 super(name, age,state,gender);
 		 this.parent_id1 = parent_id1;
 	        this.parent_id2 = parent_id2;
 		}

    public YoungChild()
    {

    }
    public String getParent_id1() {
        return parent_id1;
    }

    public void setParent_id1(String parent_id1) {
        this.parent_id1 = parent_id1;
    }

    public String getParent_id2() {
        return parent_id2;
    }

    public void setParent_id2(String parent_id2) {
        this.parent_id2 = parent_id2;
    }


}
