import sys
sys.setrecursionlimit(10**8)

class Node:
    def __init__(self, data, left_node, right_node):
        self.data = data
        self.left = left_node
        self.right = right_node

def pre_order(node, temp):
    temp.append(node.data[2])
    if node.left:
        pre_order(node.left, temp)
    if node.right:
        pre_order(node.right, temp)
    return temp

def post_order(node, temp):
    if node.left:
        post_order(node.left, temp)
    if node.right:
        post_order(node.right, temp)
    temp.append(node.data[2])
    return temp
        
def solution(nodeinfo):
    answer = []
    root = None
    for i in range(len(nodeinfo)):
        nodeinfo[i].append(i+1)
    nodeinfo = sorted(nodeinfo, key = lambda x : -x[1])
    
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
    answer.append(pre_order(root, []))
    answer.append(post_order(root, []))
    return answer