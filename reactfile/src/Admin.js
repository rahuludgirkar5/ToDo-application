import React, { Component } from 'react'
import { NavLink, Redirect } from 'react-router-dom'
import ToDoAPI from './ToDoAPI'

class Admin extends Component {

    constructor(props) {
        super(props)
    
        let admintoken = sessionStorage.getItem("admintoken")
        this.state = {
            userlist:[],
            adminlog:admintoken
        }
        
    }

    componentDidMount(){
        if(this.state.adminlog){
        ToDoAPI.getUserList()
        .then(res => {
            if(res.data!==null){
                this.setState({userlist:res.data})
            }
        })
        .catch(error => {
            console.log("Something Wrong")
            console.log(error)
        })
    }
    }
    activateUser (email,password,e){
        let idpass={
            email:email,
            password:password
        }
        ToDoAPI.userActivate(JSON.stringify(idpass))
        .then(res =>{
            if(res.data==="User Activation Successfull"){
                alert(res.data)
                this.setState({userlist:this.state.userlist.filter(user =>user.status==="Deactivated"?user.email===email?user.status="Active":user.status="Deactivated":user)})
            }
        })
        .catch(error =>{
            alert("Something Wrong")
            console.log(error)
        })
        
    }
    deactivateUser (email,password,e){
        let idpass={
            email:email,
            password:password
        }
        ToDoAPI.userDeactivate(JSON.stringify(idpass))
        .then(res =>{
            if(res.status===200){
                alert(res.data)
                this.setState({userlist:this.state.userlist.filter(user =>user.status==="Active"?user.email===email?user.status="Deactivated":user.status==="Active":user)})
            }
        })
        .catch(error =>{
            console.log(error)
            alert("Something Wrong")
        })
        
    }
    
    render() {
        if(this.state.adminlog){
        return (<div>
        <div style={{textAlign:"center",paddingLeft:"100px"}}>
        <NavLink to="/report" className="btn btn-primary" style={{}}>Report</NavLink>
        <NavLink to="/logout" className="btn btn-primary">Logout</NavLink></div>
                 <div>
                    <table>
                    <tbody>
                    <tr>
                            <th>User ID</th>
                            <th>Firstname</th>
                            <th>Lastname</th>
                            <th>Gender</th>
                            <th>DOB</th>
                            <th>Email</th>
                            <th>Status</th>
                            <th>Action Activate</th>
                            <th>Action Deactivate</th>
                            </tr>
                        {
                            this.state.userlist === null ? null : this.state.userlist.map((User, i) => {
                                return<tr key={i}>
                                    <td>{User.userId}</td>
                                    <td>{User.firstname}</td>
                                    <td>{User.lastname}</td>
                                    <td>{User.gender}</td>
                                    <td>{User.dob}</td>
                                    <td>{User.email}</td>
                                    <td>{User.status}</td>
                                    
                                   <td> <button onClick={this.activateUser.bind(this,User.email,User.password)} className="btn btn-primary">Activate</button></td>
                                   <td><button onClick={this.deactivateUser.bind(this,User.email,User.password)} className="btn btn-danger">Deactivate</button></td>
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

export default Admin
