package net.htjs.action.cxtj.zdsy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 2017/11/8.
 */
public class User
{
    private String firstname;
    private String lastname;
    private int age;

    public User()
    {

    }

    public User(String firstname, String lastname, int age)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
    }

    public String getFirstname()
    {
        return firstname;
    }
    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }
    public String getLastname()
    {
        return lastname;
    }
    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }
    public int getAge()
    {
        return age;
    }
    public void setAge(int age)
    {
        this.age = age;
    }

    public List<User> findAllUser()
    {
        User user1 = new User("张","无极",22);
        User user2 = new User("张11","无极",33);
        User user3 = new User("张222","无极",44);

        List<User> list = new ArrayList<User>();

        list.add(user1);
        list.add(user2);
        list.add(user3);
        return list;
    }
}
