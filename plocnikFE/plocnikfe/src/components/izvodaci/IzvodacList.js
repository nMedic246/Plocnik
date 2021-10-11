import React, {Component} from 'react';
import izvodacService from '../services/izvodacService'
import IzvodacSum from './izvodacSum'
import "../css/Card.css"
import "../css/Sum.css";
import izvodac from './izvodac';

class IzvodacList extends Component{
    constructor(props){
        super(props);
        this.state = {
            izvodaci: [],
            filter:''
        }
        this.handleChange = this.handleChange.bind(this);
        this.handleSearch = this.handleSearch.bind(this);
    }

    componentDidMount(){
       izvodacService.getIzvodaci()
       .then(async response => {
        const data = await response.json();
        if (!response.ok) {
            const error = (data && data.message) || response.statusText;
            return Promise.reject(error);
        }
        this.setState({ 
            izvodaci:data
         })
    })
    .catch(error => {
        this.setState({ errorMessage: error.toString() });
        console.error('There was an error!', error);
    });
    }

    handleChange(e){
        const { name, value } = e.target;
         this.setState({ [name]: value });
      }
    handleSearch(e){
        e.preventDefault();
        const {filter} =this.state;
        izvodacService.searchIzvodaci(filter).then(res=>res.json())
        .then(res=>{
          this.setState({
            izvodaci:res
          })
        });
      }
    
    render(){
        const {izvodaci} =this.state;

        const izvodaciList = izvodaci.length ? 
        (
            izvodaci.map(izvodac => {
                return (
                    <IzvodacSum izvodac={izvodac} key={izvodac.idIzvodac}/>   
                )
            })
        ):(
            <div>Nema rezultata</div>
        )
        
        return(
             <div class="container" >
                 <div class="box">
                 <div className="component">
                    <div className="row row-filter">
                    <div className="filter col s6 search">
                    
                    <div className="col s1">
                        <i className="material-icons search pinkIcon">search</i>
                        <i class="material-icons search blueIcon">search</i>
                    </div>
                    <div className="col s8">
                        <input placeholder="Pretraži izvođače" id="first_name" name="filter" value={this.state.filter} onChange={this.handleChange} type="text" />
                    </div>
                    <div>
                        <button class="btn waves-effect waves-light" onClick={this.handleSearch}>
                        Pretraži
                        </button>
                    </div>
                    </div>
                    </div>
                    </div>
                 </div>
               <div class="box container-card">
                {izvodaciList}
                </div>  
            </div>
        )
    }
}
export default IzvodacList;