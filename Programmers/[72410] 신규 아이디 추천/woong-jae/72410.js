function solution(new_id) {
    // 1.
    let recommend_id = new_id.toLowerCase();
    // 2.
    recommend_id = recommend_id.split("").filter(char => /[\w\-\.]/.test(char));
    // 3. 4.
    recommend_id = recommend_id.reduce((prev, cur) => {
        if(cur !== "." || (prev.length > 0 && prev[prev.length - 1] !== ".")) {
            prev.push(cur);
        };
        return prev;
    }, []);
    if(recommend_id[recommend_id.length - 1] === ".") recommend_id.pop();
    // 5.
    if(recommend_id.length === 0) recommend_id.push("a");
    // 6.
    if(recommend_id.length >= 16) {
        recommend_id = recommend_id.slice(0, recommend_id[14] === "." ? 14 : 15);
    }
    // 7.
    while(recommend_id.length < 3) recommend_id.push(recommend_id[recommend_id.length - 1]);
    
    return recommend_id.join("");
}