import React from 'react'
import { NavLink } from 'react-router-dom'

const SignedOutLinks = () => {
  return (
    <div>
      <ul className="right">
      <li><NavLink to='/listaPloca'>Vinyl ploče</NavLink></li>
      <li><NavLink to='/listaIzvodaca'>Izvođači</NavLink></li>
      <li><NavLink to='/listaIzdavackihKuca'>Izdavačke kuće</NavLink></li>
      <li><NavLink to='/register'>Registriraj se</NavLink></li>
      <li><NavLink to='/login'>Prijavi se</NavLink></li>
      </ul>
    </div>
  )
}

export default SignedOutLinks