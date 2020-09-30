import React from 'react';

import './App.css';
import Register from './Register';
import { Switch, Route } from 'react-router-dom';
import Login from './Login';
import UserAccount from './UserAccount';
import Admin from './Admin';
import Report from './Report';
import Logout from './Logout';
import Home from './Home';


function App() {
  return (
    <div className="App">
    <Switch>
    <Route exact path="/" component={Home} />
    <Route exact path="/login" component={Login} />
    <Route exact path="/register" component={Register} />
    <Route exact path="/useraccount" component={UserAccount} />
    <Route exact path="/admin" component={Admin} />
    <Route exact path="/report" component={Report} />
    <Route exact path="/logout" component={Logout} />
    </Switch>
     
    </div>
  );
}

export default App;
