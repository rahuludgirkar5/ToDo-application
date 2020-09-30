import axios from 'axios';

class ToDoAPI{

    addUser(user){
        return axios.post('http://localhost:8080/Java Servlet/register',user);
    }

    Login(idpass){
        return axios.post("http://localhost:8080/Java Servlet/login",idpass);
    }

    getToDoList(email){
        return axios.get("http://localhost:8080/Java Servlet/ToDoServlet?email="+email)
    }

    getUserList(){
        return axios.get("http://localhost:8080/Java Servlet/UserList")
    }

    addTask(task){
        return axios.post("http://localhost:8080/Java Servlet/addtask",task);
    }

    changeTaskStatus(taskid){
        return axios.get("http://localhost:8080/Java Servlet/changetaskstatus?taskid="+taskid);
    }
    deleteTask(taskid){
        return axios.get("http://localhost:8080/Java Servlet/deletetask?taskid="+taskid);
    }
    userActivate(idpass){
        return axios.post("http://localhost:8080/Java Servlet/ActivateUser",idpass)
    }

    userDeactivate(idpass){
        return axios.post('http://localhost:8080/Java Servlet/DeactivateUser',idpass);
    }

    Report(){
        return axios.get("http://localhost:8080/Java Servlet/report");
    }

    otpGenarate(email){
        return axios.get("http://localhost:8080/Java Servlet/OTPGeneration?email="+email)
    }
}

export default new ToDoAPI();