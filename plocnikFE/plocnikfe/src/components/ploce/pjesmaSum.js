import React from 'react'
import { Component } from 'react';
import pjesmaService from '../services/pjesmaService';

class PjesmaSum extends Component{
  constructor(props){
    super(props);
    this.state={
      pjesma:props.pjesma.idPjesma,
      rbr:props.pjesma.rednibr,
      producenti:[]
    }
  }
  componentDidMount(){
    pjesmaService.getProducenti(this.props.pjesma.idPjesma.idPjesma)
    .then(async response => {
      const data = await response.json();
      if (!response.ok) {
          const error = (data && data.message) || response.statusText;
          return Promise.reject(error);
      }
      this.setState({ producenti:data })
  })
  .catch(error => {
      this.setState({ errorMessage: error.toString() });
      console.error('There was an error!', error);
  });
  }

  render(){
    const {pjesma,rbr,producenti}=this.state;
    var postojiT = pjesma.trajanjePjesma ? (<div>{pjesma.trajanjePjesma.slice(3)}</div>):(<div></div>)
    return (
      <tr>
      <td>{rbr}.</td>    
      <td>{pjesma.nazivPjesma}</td>
      <td>{postojiT}</td>
      <td> { producenti.length ? ( producenti.map(producent => {
                return (
                  <p>{producent.idProducent.nazivProducent}</p>
                )
              })):(<p>Nema podataka</p>)}
      </td>
      </tr>
    )
  }
}

export default PjesmaSum;
