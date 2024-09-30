
export function parseDateTime(datetime: number){
    
    if(!datetime || datetime==0){
        return '';
    }
    const time = datetime.toString();
    let year = time.substring(0, 4);
    let month = time.substring(4, 6);
    let day = time.substring(6, 8);
    let hour = time.substring(8, 10);
    let minute = time.substring(10, 12);
    if(time.length==12){
        return year + '-' + month + '-' + day + ' ' + hour + ':' + minute;
    } 
    let second = time.substring(12, 14);
    return year + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
    
}