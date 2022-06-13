function solution(record) {
    const lastNickname = new Map();
    const stack = [];
    
    record.forEach(rec => {
        const [action, uid, nickname] = rec.split(" ");
        if(!lastNickname.has(uid)) lastNickname.set(uid, nickname);
        
        switch(action) {
            case "Enter":
                if(lastNickname.get(uid) !== nickname) lastNickname.set(uid, nickname);
            case "Leave":
                stack.push({ uid, action });
                break;
            case "Change":
                lastNickname.set(uid, nickname);
                break;
        }
    });
    
    return stack.map(({ uid, action }) => {
        const suffix = "Enter" === action ? "들어왔습니다" : "나갔습니다";
        return `${lastNickname.get(uid)}님이 ${suffix}.`;
    });
}