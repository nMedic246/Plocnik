class pjesmaService{
    getProducenti(id){
        const requestOptions={
            method:'GET'
        }
        return fetch('/pjesma/'+id+'/producent',requestOptions);
    }
}
export default new pjesmaService();