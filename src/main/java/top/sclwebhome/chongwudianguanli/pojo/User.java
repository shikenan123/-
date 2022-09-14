package top.sclwebhome.chongwudianguanli.pojo;

public class User {
    Integer id;
    String  username;
    String  sex;
    Integer  age;
    Integer  UID;
    String  password;
    String created;
    String phone;
    Integer classify;
    public User() {
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", UID=" + UID +
                ", password='" + password + '\'' +
                ", created='" + created + '\'' +
                ", phone='" + phone + '\'' +
                ", classify=" + classify +
                '}';
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public Integer getUID() {
        return UID;
    }
    public void setUID(Integer UID) {
        this.UID = UID;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getCreated() {
        return created;
    }
    public void setCreated(String created) {
        this.created = created;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Integer getClassify() {
        return classify;
    }
    public void setClassify(Integer classify) {
        this.classify = classify;
    }
    public User(Integer id, String username, String sex, Integer age, Integer UID, String password, String created, String phone, Integer classify) {
        this.id = id;
        this.username = username;
        this.sex = sex;
        this.age = age;
        this.UID = UID;
        this.password = password;
        this.created = created;
        this.phone = phone;
        this.classify = classify;
    }
}
