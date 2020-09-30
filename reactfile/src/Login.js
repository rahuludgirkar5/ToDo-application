import React, { Component } from 'react'
import { Redirect,NavLink } from 'react-router-dom'
import ToDoAPI from './ToDoAPI'

class Login extends Component {
    constructor(props) {
        super(props)

        this.state = {

            email: '',
            password: '',
            adminlog: false,
            loggedin: false
        }
    }

    changeHandler=(e)=>{
        this.setState({
            [e.target.name]:e.target.value
        })
    }
    
    checkLog = (e) =>{
        e.preventDefault()
        let idpass={
            email:this.state.email,
            password:this.state.password
        }
        ToDoAPI.Login(JSON.stringify(idpass))
        .then(res =>{
            if(res.status===200){
                console.log(res.data)
                let data=res.data
                if(data==="Not Match"){
                    alert("Either You are not registered or Email and Password could be wrong.."+
                    "\nOr Your Account could be Deactivated by Admin Please wait for some time to activate again")
                }
                else if(data==="Admin"){
                    sessionStorage.setItem("admintoken",true)
                    this.setState({adminlog:true})
                }
                else if(data==="user"){
                    sessionStorage.setItem("usertoken",idpass.email)
                    sessionStorage.setItem("userlogtoken",true)
                    this.setState({loggedin:true})
                }
            }
            else{
                alert("Something's Wrong")
            }
        })
        .catch(error =>{
            alert("Error While Fetching Data : Server Side Problem")
            console.log(error)
        })

    }
    render() {
        if(this.state.loggedin){
            return <Redirect to="/useraccount" />
        }
        if(this.state.adminlog){
            return <Redirect to="/admin" />
        }
        return (
            <div>
                <div className="container contact_div">
                    <div className="row">
                        <div className="col-md-6 col-10 mx-auto">
                            <form onSubmit={this.checkLog} >
                                <div className="mb-3">
                                    <label className="form-lable">Email :</label><br />
                                    <input required type="text" name="email" value={this.state.email} onChange={this.changeHandler} className="form-control" /><br />
                                </div>
                                <div className="mb-3">
                                    <label className="form-lable">Password :</label><br />
                                    <input required type="password" name="password" value={this.state.password} onChange={this.changeHandler} className="form-control" /><br />
                                </div>
                                <button type="submit"  className="btn btn-primary" >Login</button><br /><br />
                                <NavLink to="/register" className="btn btn-primary">New User Register Here</NavLink>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default Login
