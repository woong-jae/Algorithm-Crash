function solution(files) {
    const splitFileName = fileName => {
        let matched = fileName.match(/\d+/);
        return [fileName.slice(0, matched.index).toLowerCase() , +matched];
    }
    
    files.sort((a, b) => {
        a = splitFileName(a);
        b = splitFileName(b);
        if(a[0] === b[0]) return a[1] - b[1];
        return a[0].localeCompare(b[0]);
    });
    
    return files;
}