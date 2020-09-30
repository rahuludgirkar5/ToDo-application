import React, { Component } from 'react'
import Admin from './Admin'
import UserAccount from './UserAccount'
import Login from './Login'

class Home extends Component {
  constructor() {
    super()
    this.state = {

      adminlog: true,
      loggedin: true

    }
  }

  static getDerivedStateFromProps(props, state) {

    let token1 = sessionStorage.getItem("usertoken")
    let admintoken = sessionStorage.getItem("admintoken")

    if (admintoken == null) {
      if (token1 == null) {
        return { adminlog: false, loggedin: false }
      }
      else {
        return { adminlog: false, loggedin: true }
      }
    }
    else {
      return { adminlog: true }
    }

  }
  render() {
    if (this.state.adminlog) {
      return <div>
        <center>
        <h2>ToDoApp</h2>
        </center>
        <Admin />
      </div>
    }
    else if (this.state.loggedin) {
      return <div>
        <center>
        <h2>ToDoApp</h2>
        </center>
        <UserAccount />
      </div>
    }
    else {
      return (<div>
        <center>
        <h2>ToDoApp</h2>
        </center>
        <Login />
      </div>
      )
    }
  }
}

export default Home
