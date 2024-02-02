import axios from "axios"

export const getStationsAxios = (setData) => {
  axios.get("http://localhost:8080/api/bike-station/getList")
    .then((response) => {
      setData(response.data)
      console.log(response.data)
    })
    .catch((error) => console.log(error))
}



