import React, { Component } from 'react'
import { NavLink, Redirect } from 'react-router-dom'
import ToDoAPI from './ToDoAPI'

class UserAccount extends Component {

    constructor(props) {
        super(props)
        let userlogtoken=sessionStorage.getItem("userlogtoken")
        this.state = {
            taskid:'',
            taskname: '',
            description: '',
            tasklist: [],
            userlog:userlogtoken,
            
        }
        
    }
    changeHandler = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    componentDidMount(){
        if(this.state.userlog){
            this.loadTask();
        }
    }

    loadTask(){
        let email=sessionStorage.getItem("usertoken")
        ToDoAPI.getToDoList(email)
        .then(res =>{
            if(res.status===200){
                console.log(res.data)
                let data=res.data
                
                if(data!==null && data!=="No List"){
                this.setState({tasklist:data})

                }
            }
        })
        .catch(error =>{
            console.log("Error While fetching data")
        })
    }
    submitTask = (e) => {
        e.preventDefault()
        let email=sessionStorage.getItem("usertoken")
        let task ={
            taskname:this.state.taskname,
            description:this.state.description,
            email:email
        }
        task = JSON.stringify(task);
        ToDoAPI.addTask(task)
        .then(res => {
            if(res.status===200){
                alert("Task Added To List Successfully")
                this.loadTask();
            }
        })
        .catch(error => {
            alert("something Wrong");
            console.log(error)
        })

    }
    changeStatus(taskid,e) {
        e.preventDefault()
        ToDoAPI.changeTaskStatus(taskid)
        .then(res => {
            if(res.status===200){
                alert("Task Completed ")
                this.setState({tasklist:this.state.tasklist.filter(task => task.status==="Pending"?task.taskid===taskid?task.status="Complete":task.status="Pending":task)})
            }
        })
        .catch(error =>{
            alert("Problem Occurred Task Not Complete")
            console.log(error)
        })

    }

    editTask(taskid,taskname,desc,e) {
        e.preventDefault()
        for(let i=0;i<this.state.tasklist.length;i++){
            if(this.state.tasklist[i].taskid===taskid){
                console.log(this.state.tasklist[i].taskid)
                this.setState({
                    taskname:taskname,
                    description:desc
                })
                break;
            }
        }
        this.deletefromlist(taskid);
    }

    deletefromlist(taskid){
        ToDoAPI.deleteTask(taskid)
        .then(res => {
            let data=res.data
            if(res.status===200){
                if(data==="Deleted"){
                    alert("Update Task and Save it ")
                }
            }
        })
        .catch(error =>{
            alert("Problem Occurred Task Not Deleted")
            console.log(error)
        })
    }
    daleteTask(taskid,e) {
        e.preventDefault()
        this.deletefromlist(taskid);
    }
    render() {
         if(this.state.userlog){
        return (
            <div>
            <div style={{textAlign:"right",paddingRight:"auto"}}>
            <NavLink to="/logout" className="btn btn-primary" >Logout</NavLink></div><br/>
                <form onSubmit={this.submitTask}>
                    <label>TaskName</label>
                    <input type="text" required value={this.state.taskname} onChange={this.changeHandler} name="taskname"></input>
                    <label>Description</label>
                    <input type="text" required value={this.state.description} onChange={this.changeHandler} name="description"></input>
                    <input type="submit" value="Submit" ></input>
                </form>
                <br />
                <div>
                    <table>
                    <tbody>
                        <tr>
                            <th>Check</th>
                            <th>Task Id</th>
                            <th>taskname</th>
                            <th>description</th>
                            <th>status</th>
                            <th>Edit Action</th>
                            <th>Delete Action</th>
                        </tr>
                        {
                            this.state.tasklist === null ? null : this.state.tasklist.map((task, i) => {
                                return<tr key={i}>
                                    <td><input type="checkbox" checked={task.status==="Pending"?false:true} onChange={this.changeStatus.bind(this,task.taskid)} ></input></td>
                                    <td>{task.taskid}</td>
                                    <td>{task.taskname}</td>
                                    <td>{task.description}</td>
                                    <td>{task.status}</td>
                                    <td><button onClick={this.editTask.bind(this,task.taskid,task.taskname,task.description)} className="btn btn-primary">Edit</button></td>
                                    <td><button onClick={this.daleteTask.bind(this,task.taskid)} className="btn btn-danger">Delete</button></td>
                                </tr>
                            })
                        }
                        </tbody>
                    </table>
                </div>

            </div>
        )
        }
        else{
            return <Redirect to="/" />
        }
    }
}

export default UserAccount
