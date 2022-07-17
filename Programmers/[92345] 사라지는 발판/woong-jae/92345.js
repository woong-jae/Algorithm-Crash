function solution(board, aloc, bloc) {
  const d = [[-1, 0], [0 ,1], [1, 0], [0, -1]];
  
  const valid = ([nr, nc]) => {
      if(0 <= nr && nr < board.length && 0 <= nc && nc < board[0].length) return true;
      return false;
  }
  
  const finished = (loc) => {
      for(let dir = 0; dir < 4; dir++) {
          const [nr, nc] = loc.map((pos, i) => pos + d[dir][i]);
          if(valid([nr, nc]) && board[nr][nc]) return false;
      }
      return true;
  }
  
  const simulate = (aloc, bloc) => {
      if(finished(aloc)) return [false, 0];
      if(aloc[0] === bloc[0] && aloc[1] === bloc[1]) return [true, 1];
      
      let canWin = false;
      let maxTurn = 0, minTurn = Infinity;
      
      board[aloc[0]][aloc[1]] = 0;
      for(let dir = 0; dir < 4; dir++) {
          const [nr, nc] = aloc.map((pos, i) => pos + d[dir][i]);
          if(!valid([nr, nc]) || board[nr][nc] === 0) continue;
          
          const [isWin, turn] = simulate(bloc, [nr, nc]);
          
          if(!isWin) {
              canWin = true;
              minTurn = Math.min(minTurn, turn);
          }
          else {
              maxTurn = Math.max(maxTurn, turn);
          }
      }
      board[aloc[0]][aloc[1]] = 1;
      
      return [canWin, (canWin ? minTurn : maxTurn) + 1];
  }
  
  return simulate(aloc, bloc)[1];
}