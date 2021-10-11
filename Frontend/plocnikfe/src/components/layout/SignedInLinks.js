import React, { Component } from 'react'
import { NavLink } from 'react-router-dom'
import {userActions} from "../actions/userActions"
import M from 'materialize-css'
import {connect} from 'react-redux';
import Dropdown from 'react-bootstrap/Dropdown'
import DropdownButton from 'react-bootstrap/DropdownButton'

class SignedInLinks extends Component {
  constructor(props){
    super(props);
    this.state={
      user: JSON.parse(localStorage.getItem('user'))
    }
 }
 componentDidMount() {
    M.AutoInit();
  } 
componentDidUpdate(){
  this.setState={
    user:JSON.parse(localStorage.getItem('user'))
  }
}
  render(){
    const {user} = this.state;
    const admin = user.isAdmin ? (
      <li>
       <a class='dropdown-trigger btn' href='#' data-target='dropdown1'>Dodaj sadržaj</a>
      <ul id='dropdown1' class='dropdown-content'>
        <li><NavLink to='/admin/dodajPlocu'>Vinyl ploča</NavLink></li>
        <li><NavLink to='/admin/dodajIzvodaca'>Izvođač</NavLink></li>
        <li><NavLink to='/admin/dodajIzdavackuKucu'>Izdavačka kuća</NavLink></li>
      </ul>
      </li>
     
    )
    :(<li><NavLink to={'/korisnik/'+user.korisnickoIme} className="btn btn-floating pink lighten-1">{user.korisnickoIme.substring(0,1)}</NavLink></li>)
  
    return (
      <div>
        <ul className="right">
          <li><NavLink to='/listaPloca'>Vinyl ploče</NavLink></li>
          <li><NavLink to='/listaIzvodaca'>Izvođači</NavLink></li>
          <li><NavLink to='/listaIzdavackihKuca'>Izdavačke kuće</NavLink></li>
          <li><a href="/" className="nav-link" onClick={userActions.logout}>Odjava</a></li>
          {admin}
        </ul>
      </div>
    )
  }
 
}


export default SignedInLinks;