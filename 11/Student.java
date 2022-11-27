import java.util.List;

public class Student {

    private int age;
    private int weight;
    private int course;
    private String name;
    private String groupNum;
    private List<Course> courses;

    public Student() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(String groupNum) {
        this.groupNum = groupNum;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String toString() {
        return "Student(name=" + name + ", age=" + age + ", weight=" + weight + ", groupNum=" + groupNum + ", weight=" + weight + ", courses=" + courses + ')';
    }

    public class Course {
        private String name;
        private String description;

        public Course() {
        }

        public String toString() {
            return "Course(name=" + name + ", description=" + description + ')';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
