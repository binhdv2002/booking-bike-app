export const getStartTime = (startTime) => {
  let time = `${startTime[3]}:${startTime[4]}:${startTime[5]}`
  let month = startTime[1]
  if(month < 10) {
    month = "0"+month
  }
  let date = `${startTime[0]}-${month}-${startTime[2]}`
  return time + ' '+ date;
}


export const getEndTime = (UNIX_timestamp) => {
  var a = new Date(UNIX_timestamp);

  var year = a.getFullYear();
  var month = a.getMonth();
  if(month < 10) {
    month = "0"+month
  }
  var date = a.getDate();
  var hour = a.getHours();
  var min = a.getMinutes();
  var sec = a.getSeconds();
  var time = hour + ':' + min + ':' + sec+ " "+ year + '-' + month + '-' + date;
  return time;
}