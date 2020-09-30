import React, { Component } from 'react'
import { Redirect } from 'react-router-dom'

 class Logout extends Component {
     constructor(){
         super()
        const admintoken=sessionStorage.getItem("admintoken")
        const userlogtoken=sessionStorage.getItem("userlogtoken")

        if(admintoken!=null){
            sessionStorage.removeItem("admintoken")
        }
        if(userlogtoken!=null){
            sessionStorage.removeItem("usertoken")
            sessionStorage.removeItem("userlogtoken")
        }
     }
    render() {
        return (
            <div>
                <Redirect to="/" ></Redirect>
            </div>
        )
    }
}

export default Logout
