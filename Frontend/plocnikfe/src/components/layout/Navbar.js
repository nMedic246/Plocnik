import React from 'react';
import {Link} from "react-router-dom";
import SignedInLinks from './SignedInLinks'
import SignedOutLinks from './SignedOutLinks'
import "./Navbar.css";
import { authentication } from '../reducers/authReducer';
import {connect} from "react-redux";

const Navbar = (props) => {
  const {loggedIn} = props;
  const links = loggedIn ? <SignedInLinks/> : <SignedOutLinks/>
    return (
        <nav className="nav-wrapper">
          <div className="container">
            <Link to='/' className="brand-logo" >Pločnik</Link>
            {links}
          </div>
        </nav>
      )
 }

function mapStateToProps(state){
  const{loggedIn} = state.authentication;
  return {loggedIn}
}


export default connect(mapStateToProps)(Navbar);