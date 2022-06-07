# [42892] 길 찾기 게임 - Python

## 🔍 Algorithm
**Binary Tree**

## 💻 Logic

```Python
class Node:
    def __init__(self, data, left_node, right_node):
        self.data = data
        self.left = left_node
        self.right = right_node
```
- **Node 클래스 생성**  

```Python
    for x, y, i in nodeinfo:
        tree = Node([x,y,i], None, None)
        # root가 없는 경우
        if root == None:
            root = tree
        # root가 있는 경우
        else:
            cur = root
            while cur:
                # 오른쪽 체크
                if cur.data[0] < tree.data[0]:
                    if cur.right == None:
                        cur.right = tree
                        break
                    else:
                        cur = cur.right
                # 왼쪽 체크
                else:
                    if cur.left == None:
                        cur.left = tree
                        break
                    else:
                        cur = cur.left
```
- **Tree 생성**  

```Python
def pre_order(node, temp):
    temp.append(node.data[2])
    if node.left:
        pre_order(node.left, temp)
    if node.right:
        pre_order(node.right, temp)
    return temp
```
- **preorder 탐색 함수**  

```Python
def post_order(node, temp):
    if node.left:
        post_order(node.left, temp)
    if node.right:
        post_order(node.right, temp)
    temp.append(node.data[2])
    return temp
```
- **postorder 탐색 함수**  


## 📝 Review

수업 시간에 했었던 Binary Tree, Preorder, Postorder 구현과 같아서 특별히 어려운 점은 없었다.  
추가로 처리해야 하는 부분이라면 처음 시작할 때, y좌표 기준으로 내림차순 정렬해야 하는 점
