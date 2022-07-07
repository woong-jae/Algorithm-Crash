function solution(fees, records) {
    const [base_min, base_fee, unit_min, unit_fee] = fees;
    const total_min = new Map();
    const in_records = new Map();
    
    const toMinute = time => {
        const [hour, min] = time.split(":");
        return +hour * 60 + +min;
    };
    
    records.forEach(record => {
        const [time, car_num, action] = record.split(" ");
        if(action === "IN") {
            in_records.set(car_num, toMinute(time));
        }
        else {
            const used_time = toMinute(time) - in_records.get(car_num);
            in_records.delete(car_num);
            
            if(!total_min.has(car_num)) total_min.set(car_num, 0);
            total_min.set(car_num, total_min.get(car_num) + used_time);
        }
    });
    in_records.forEach((in_time, car_num) => {
        const used_time = toMinute("23:59") - in_time;
        if(!total_min.has(car_num)) total_min.set(car_num, 0);
        total_min.set(car_num, total_min.get(car_num) + used_time);
    });
    
    return Array.from(total_min)
        .sort((a, b) => a[0].localeCompare(b[0]))
        .map(([_, min]) => {
            if(min <= base_min) return base_fee;
            return base_fee + Math.ceil((min - base_min) / unit_min) * unit_fee; 
        });
}