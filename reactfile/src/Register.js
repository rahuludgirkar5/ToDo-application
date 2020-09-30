import React, { Component } from 'react'
import ToDoAPI from './ToDoAPI'
import { Redirect } from 'react-router-dom'

class Register extends Component {
    constructor(props) {
        super(props)

        this.state = {
            firstname: "",
            lastname: "",
            gender: "Male",
            dob: "",
            email: "",
            password: "",
            confirmpass: "",
            registercomplete: false
        }
    }

    changeHandler = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    registerData() {

        let user = {
            firstname: this.state.firstname,
            lastname: this.state.lastname,
            gender: this.state.gender,
            dob: this.state.dob,
            email: this.state.email,
            password: this.state.password
        }

        ToDoAPI.addUser(JSON.stringify(user)).then(res => {
            if (res.status === 200) {
                console.log(res.data)
                alert(res.data)
                this.setState({ registercomplete: true })
            }
        })
            .catch(error => {
                console.log(error)
            })

    }

    submitform = (e) => {
        e.preventDefault();
        let passpattern = /.{4,10}/
        let pattern = /^[a-zA-Z]{1}[a-zA-Z0-9_\.]*@([a-zA-Z]+\.)+[a-zA-Z]+$/;
        if (pattern.test(this.state.email) && passpattern.test(this.state.password)) {
            if (this.state.password === this.state.confirmpass) {
                ToDoAPI.otpGenarate(this.state.email)
                    .then(res => {
                        if (res.status === 200) {
                            let SysOTP = res.data
                            let userOTP = prompt("Please Check Your Email And Enter the OTP(One Time Password)")
                            if (SysOTP === userOTP) {
                                this.registerData();
                            }
                            else {
                                alert("Wrong OTP ! please Register Again")
                            }
                        }

                    })
                    .catch(error => {
                        alert("Something Wrong ! Server is not Responding")
                        console.log(error)
                    })
            }
            else {
                alert("Password Not Matching ! Enter Same Password In Both Field ")
            }
        }
        else {
            if (pattern.test(this.state.email)) {
                alert("Password Min 4 and Max 10 Character")
            }
            else {
                alert("Email format is not correct Please Enter in follwing format\n eg. as12@abc.com or ab12@abc.co.in")
            }
        }
    }
    render() {
        if (this.state.registercomplete) {
            return <Redirect to="/login" />
        }
        return (
            <div>
                <div className="container ">
                    <div className="row">
                        <div className="col-md-6 col-10 mx-auto">
                            <form onSubmit={this.submitform}>
                                <div className="mb-3">
                                    <label className="form-lable">First Name :</label>
                                    <input type="text" required value={this.state.firstname} onChange={this.changeHandler} name="firstname" className="form-control" />
                                </div>
                                <div className="mb-3">
                                    <label className="form-lable">Last Name :</label>
                                    <input type="text" required value={this.state.lastname} onChange={this.changeHandler} name="lastname" className="form-control" />
                                </div>
                                <div className="mb-3">
                                    <label className="form-lable">Gender :</label>
                                    {/* <input type="text" required value={this.state.gender} onChange={this.changeHandler} name="gender" className="form-control" placeholder="Male/Female" /> */}
                                    <select required value={this.state.gender} onChange={this.changeHandler} name="gender" className="form-control">
                                        <option value={'Male'}>Male</option>
                                        <option value={'Female'}>Female</option>
                                    </select>
                                </div>
                                <div className="mb-3">
                                    <label className="form-lable">Date Of Birth :</label>
                                    <input type="text" required value={this.state.dob} onChange={this.changeHandler} name="dob" placeholder="yyyy-mm-dd" className="form-control" />
                                    <p>eg. 1990-01-20</p>
                                </div>
                                <div className="mb-3">
                                    <label className="form-lable">Email :</label>
                                    <input type="text" required value={this.state.email} onChange={this.changeHandler} name="email" className="form-control" />
                                    <p>Email format eg. as12@abc.com or ab12@abc.co.in </p>
                                </div>
                                <div className="mb-3">
                                    <label className="form-lable">Password :</label>
                                    <input type="password" required value={this.state.password} onChange={this.changeHandler} name="password" className="form-control" />
                                    <p>Password Min 4 and Max 10 Character</p>
                                </div>
                                <div className="mb-3">
                                    <label className="form-lable">Confirm Password :</label>
                                    <input type="password" required value={this.state.confirmpass} onChange={this.changeHandler} name="confirmpass" className="form-control" />
                                </div>
                                <p>After Registration Please wait for Verification... <br></br>Enter the OTP that sent to your email</p>
                                <button type="submit" className="form-control btn btn-primary">Register</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default Register
