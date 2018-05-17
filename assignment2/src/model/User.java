package model;

public abstract class User {
	 String name;
	    int age;
	    String status;
	    String display_picture;
	    String state;
	    String gender;
	    
	    public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		
	    public User(String name, int age, String state, String gender) {
			super();
			this.name = name;
			this.age = age;
			this.state = state;
			this.gender = gender;
		}
		public User()
	    {

	    }

	    public int getAge() {
	        return age;
	    }

	    public void setAge(int age) {
	        this.age = age;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    public String getDisplay_picture() {
	        return display_picture;
	    }

	    public void setDisplay_picture(String display_picture) {
	        this.display_picture = display_picture;
	    }
}
