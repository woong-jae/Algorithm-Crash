const readline = require('readline');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

let input;

rl.on('line', function(line) {
    input = line.split(' ');
});

rl.on('close', function() {
    let a = parseInt(input[0]);
    let b = parseInt(input[1]);
    console.log(a + b);

    process.exit();
});