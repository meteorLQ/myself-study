package com.lq.study.corejava.Thread.集合不安全.Map.HashMap;


import java.util.HashMap;

/**
 * @author LQ
 * @date 2020/07/29 14:38
 */
public class MyTreeNode<K, V> extends MyEntry<K, V> {

    MyTreeNode<K, V> parent;  // red-black tree links
    MyTreeNode<K, V> left;
    MyTreeNode<K, V> right;
    MyTreeNode<K, V> prev;    // needed to unlink next upon deletion
    boolean red;

    MyTreeNode(int hash, K key, V value, Node<K, V> next) {
        super(hash, key, value, next);
    }


    /**
     * Returns root of tree containing this node.
     */
    final MyTreeNode<K, V> root() {
        for (MyTreeNode<K, V> r = this, p; ; ) {
            if ((p = r.parent) == null)
                return r;
            r = p;
        }
    }

    /**
     * Ensures that the given root is the first node of its bin.
     */
    static <K, V> void moveRootToFront(Node<K, V>[] tab, MyTreeNode<K, V> root) {
        int n;
        if (root != null && tab != null && (n = tab.length) > 0) {
            int index = (n - 1) & root.hash;
            MyTreeNode<K, V> first = (MyTreeNode<K, V>) tab[index];
            if (root != first) {
                Node<K, V> rn;
                tab[index] = root;
                MyTreeNode<K, V> rp = root.prev;
                if ((rn = root.next) != null)
                    ((MyTreeNode<K, V>) rn).prev = rp;
                if (rp != null)
                    rp.next = rn;
                if (first != null)
                    first.prev = root;
                root.next = first;
                root.prev = null;
            }
            assert checkInvariants(root);
        }
    }

    /**
     * Finds the node starting at root p with the given hash and key.
     * The kc argument caches comparableClassFor(key) upon first use
     * comparing keys.
     */
    final MyTreeNode<K, V> find(int h, Object k, Class<?> kc) {
        MyTreeNode<K, V> p = this;
        do {
            int ph, dir;
            K pk;
            MyTreeNode<K, V> pl = p.left, pr = p.right, q;
            if ((ph = p.hash) > h)
                p = pl;
            else if (ph < h)
                p = pr;
            else if ((pk = p.key) == k || (k != null && k.equals(pk)))
                return p;
            else if (pl == null)
                p = pr;
            else if (pr == null)
                p = pl;
            else if ((kc != null))
                //||
                // (kc = comparableClassFor(k)) != null) &&
                //    (dir = compareComparables(kc, k, pk)) != 0)

                // p = (dir < 0) ? pl : pr;
                p = null;
            else if ((q = pr.find(h, k, kc)) != null)
                return q;
            else
                p = pl;
        } while (p != null);
        return null;
    }

    /**
     * Calls find for root node.
     */
    final MyTreeNode<K, V> getTreeNode(int h, Object k) {
        return ((parent != null) ? root() : this).find(h, k, null);
    }

    /**
     * Tie-breaking utility for ordering insertions when equal
     * hashCodes and non-comparable. We don't require a total
     * order, just a consistent insertion rule to maintain
     * equivalence across rebalancings. Tie-breaking further than
     * necessary simplifies testing a bit.
     */
    static int tieBreakOrder(Object a, Object b) {
        int d;
        if (a == null || b == null ||
                (d = a.getClass().getName().
                        compareTo(b.getClass().getName())) == 0)
            d = (System.identityHashCode(a) <= System.identityHashCode(b) ?
                    -1 : 1);
        return d;
    }

    /**
     * Forms tree of the nodes linked from this node.
     */
    final void treeify(Node<K, V>[] tab) {
        MyTreeNode<K, V> root = null;
        for (MyTreeNode<K, V> x = this, next; x != null; x = next) {
            next = (MyTreeNode<K, V>) x.next;
            x.left = x.right = null;
            if (root == null) {
                x.parent = null;
                x.red = false;
                root = x;
            } else {
                K k = x.key;
                int h = x.hash;
                Class<?> kc = null;
                for (MyTreeNode<K, V> p = root; ; ) {
                    int dir, ph;
                    K pk = p.key;
                    if ((ph = p.hash) > h)
                        dir = -1;
                    else if (ph < h)
                        dir = 1;
                    else if ((kc == null
                            //&&
//                            (kc = comparableClassFor(k)) == null) ||
//                            (dir = compareComparables(kc, k, pk)) == 0
                    ))
                        //  dir = tieBreakOrder(k, pk);
                        dir = tieBreakOrder(k, pk);

                    MyTreeNode<K, V> xp = p;
//                    if ((p = (dir <= 0) ? p.left : p.right) == null) {
//                        x.parent = xp;
//                        if (dir <= 0)
//                            xp.left = x;
//                        else
//                            xp.right = x;
//                        root = balanceInsertion(root, x);
//                        break;
//                    }
                }
            }
        }
        moveRootToFront(tab, root);
    }

    /**
     * Returns a list of non-TreeNodes replacing those linked from
     * this node.
     */
    final Node<K, V> untreeify(HashMap<K, V> map) {
        Node<K, V> hd = null, tl = null;
//        for (Node<K, V> q = this; q != null; q = q.next) {
//            Node<K, V> p =
//                    //map.replacementNode(q, null);
//            if (tl == null)
//             //   hd = p;
//            else
//                tl.next = p;
//            tl = p;
//        }
        return hd;
    }

    /**
     * Tree version of putVal.
     */
    final MyTreeNode<K, V> putTreeVal(MyHashMap<K, V> map, Node<K, V>[] tab,
                                      int h, K k, V v) {
        Class<?> kc = null;
        boolean searched = false;
        MyTreeNode<K, V> root = (parent != null) ? root() : this;
        for (MyTreeNode<K, V> p = root; ; ) {
            int dir, ph;
            K pk;
            if ((ph = p.hash) > h)
                dir = -1;
            else if (ph < h)
                dir = 1;
            else if ((pk = p.key) == k || (k != null && k.equals(pk)))
                return p;
            else if ((kc == null
                    //&&
                    //(kc = comparableClassFor(k)) == null) ||
                    // (dir = compareComparables(kc, k, pk)) == 0
            )) {
                if (!searched) {
                    MyTreeNode<K, V> q, ch;
                    searched = true;
                    if (((ch = p.left) != null &&
                            (q = ch.find(h, k, kc)) != null) ||
                            ((ch = p.right) != null &&
                                    (q = ch.find(h, k, kc)) != null))
                        return q;
                }
                dir = tieBreakOrder(k, pk);
            }

            MyTreeNode<K, V> xp = p;
//            if ((p = (dir <= 0) ? p.left : p.right) == null) {
//                Node<K, V> xpn = xp.next;
//                // MyTreeNode<K, V> x = map.newTreeNode(h, k, v, xpn);
//                MyTreeNode<K, V> x = null;
//                if (dir <= 0)
//                    xp.left = x;
//                else
//                    xp.right = x;
//                xp.next = x;
//                x.parent = x.prev = xp;
//                if (xpn != null)
//                    ((MyTreeNode<K, V>) xpn).prev = x;
//                moveRootToFront(tab, balanceInsertion(root, x));
//                return null;
//            }
        }
    }

    /**
     * Removes the given node, that must be present before this call.
     * This is messier than typical red-black deletion code because we
     * cannot swap the contents of an interior node with a leaf
     * successor that is pinned by "next" pointers that are accessible
     * independently during traversal. So instead we swap the tree
     * linkages. If the current tree appears to have too few nodes,
     * the bin is converted back to a plain bin. (The test triggers
     * somewhere between 2 and 6 nodes, depending on tree structure).
     */
    final void removeTreeNode(HashMap<K, V> map, Node<K, V>[] tab,
                              boolean movable) {
        int n;
        if (tab == null || (n = tab.length) == 0)
            return;
        int index = (n - 1) & hash;
        MyTreeNode<K, V> first = (MyTreeNode<K, V>) tab[index], root = first, rl;
        MyTreeNode<K, V> succ = (MyTreeNode<K, V>) next, pred = prev;
        if (pred == null)
            tab[index] = first = succ;
        else
            pred.next = succ;
        if (succ != null)
            succ.prev = pred;
        if (first == null)
            return;
        if (root.parent != null)
            root = root.root();
        if (root == null
                || (movable
                && (root.right == null
                || (rl = root.left) == null
                || rl.left == null))) {
            tab[index] = first.untreeify(map);  // too small
            return;
        }
        MyTreeNode<K, V> p = this, pl = left, pr = right, replacement;
        if (pl != null && pr != null) {
            MyTreeNode<K, V> s = pr, sl;
            while ((sl = s.left) != null) // find successor
                s = sl;
            boolean c = s.red;
            s.red = p.red;
            p.red = c; // swap colors
            MyTreeNode<K, V> sr = s.right;
            MyTreeNode<K, V> pp = p.parent;
            if (s == pr) { // p was s's direct parent
                p.parent = s;
                s.right = p;
            } else {
                MyTreeNode<K, V> sp = s.parent;
                if ((p.parent = sp) != null) {
                    if (s == sp.left)
                        sp.left = p;
                    else
                        sp.right = p;
                }
                if ((s.right = pr) != null)
                    pr.parent = s;
            }
            p.left = null;
            if ((p.right = sr) != null)
                sr.parent = p;
            if ((s.left = pl) != null)
                pl.parent = s;
            if ((s.parent = pp) == null)
                root = s;
            else if (p == pp.left)
                pp.left = s;
            else
                pp.right = s;
            if (sr != null)
                replacement = sr;
            else
                replacement = p;
        } else if (pl != null)
            replacement = pl;
        else if (pr != null)
            replacement = pr;
        else
            replacement = p;
        if (replacement != p) {
            MyTreeNode<K, V> pp = replacement.parent = p.parent;
            if (pp == null)
                root = replacement;
            else if (p == pp.left)
                pp.left = replacement;
            else
                pp.right = replacement;
            p.left = p.right = p.parent = null;
        }

        MyTreeNode<K, V> r = p.red ? root : balanceDeletion(root, replacement);

        if (replacement == p) {  // detach
            MyTreeNode<K, V> pp = p.parent;
            p.parent = null;
            if (pp != null) {
                if (p == pp.left)
                    pp.left = null;
                else if (p == pp.right)
                    pp.right = null;
            }
        }
        if (movable)
            moveRootToFront(tab, r);
    }

    /**
     * Splits nodes in a tree bin into lower and upper tree bins,
     * or untreeifies if now too small. Called only from resize;
     * see above discussion about split bits and indices.
     *
     * @param map   the map
     * @param tab   the table for recording bin heads
     * @param index the index of the table being split
     * @param bit   the bit of hash to split on
     */
    final void split(MyHashMap<K, V> map, Node<K, V>[] tab, int index, int bit) {
        MyTreeNode<K, V> b = this;
        // Relink into lo and hi lists, preserving order
        MyTreeNode<K, V> loHead = null, loTail = null;
        MyTreeNode<K, V> hiHead = null, hiTail = null;
        int lc = 0, hc = 0;
        for (MyTreeNode<K, V> e = b, next; e != null; e = next) {
            next = (MyTreeNode<K, V>) e.next;
            e.next = null;
            if ((e.hash & bit) == 0) {
                if ((e.prev = loTail) == null)
                    loHead = e;
                else
                    loTail.next = e;
                loTail = e;
                ++lc;
            } else {
                if ((e.prev = hiTail) == null)
                    hiHead = e;
                else
                    hiTail.next = e;
                hiTail = e;
                ++hc;
            }
        }

        if (loHead != null) {
//            if (lc <= UNTREEIFY_THRESHOLD)
//                tab[index] = loHead.untreeify(map);
//            else {
//                tab[index] = loHead;
//                if (hiHead != null) // (else is already treeified)
//                    loHead.treeify(tab);
//            }
        }
        if (hiHead != null) {
//            if (hc <= UNTREEIFY_THRESHOLD)
//                tab[index + bit] = hiHead.untreeify(map);
//            else {
//                tab[index + bit] = hiHead;
//                if (loHead != null)
//                    hiHead.treeify(tab);
//            }
        }
    }

    /* ------------------------------------------------------------ */
    // Red-black tree methods, all adapted from CLR

    static <K, V> MyTreeNode<K, V> rotateLeft(MyTreeNode<K, V> root,
                                              MyTreeNode<K, V> p) {
        MyTreeNode<K, V> r, pp, rl;
        if (p != null && (r = p.right) != null) {
            if ((rl = p.right = r.left) != null)
                rl.parent = p;
            if ((pp = r.parent = p.parent) == null)
                (root = r).red = false;
            else if (pp.left == p)
                pp.left = r;
            else
                pp.right = r;
            r.left = p;
            p.parent = r;
        }
        return root;
    }

    static <K, V> MyTreeNode<K, V> rotateRight(MyTreeNode<K, V> root,
                                               MyTreeNode<K, V> p) {
        MyTreeNode<K, V> l, pp, lr;
        if (p != null && (l = p.left) != null) {
            if ((lr = p.left = l.right) != null)
                lr.parent = p;
            if ((pp = l.parent = p.parent) == null)
                (root = l).red = false;
            else if (pp.right == p)
                pp.right = l;
            else
                pp.left = l;
            l.right = p;
            p.parent = l;
        }
        return root;
    }

    static <K, V> MyTreeNode<K, V> balanceInsertion(MyTreeNode<K, V> root,
                                                    MyTreeNode<K, V> x) {
        x.red = true;
        for (MyTreeNode<K, V> xp, xpp, xppl, xppr; ; ) {
            if ((xp = x.parent) == null) {
                x.red = false;
                return x;
            } else if (!xp.red || (xpp = xp.parent) == null)
                return root;
            if (xp == (xppl = xpp.left)) {
                if ((xppr = xpp.right) != null && xppr.red) {
                    xppr.red = false;
                    xp.red = false;
                    xpp.red = true;
                    x = xpp;
                } else {
                    if (x == xp.right) {
                        root = rotateLeft(root, x = xp);
                        xpp = (xp = x.parent) == null ? null : xp.parent;
                    }
                    if (xp != null) {
                        xp.red = false;
                        if (xpp != null) {
                            xpp.red = true;
                            root = rotateRight(root, xpp);
                        }
                    }
                }
            } else {
                if (xppl != null && xppl.red) {
                    xppl.red = false;
                    xp.red = false;
                    xpp.red = true;
                    x = xpp;
                } else {
                    if (x == xp.left) {
                        root = rotateRight(root, x = xp);
                        xpp = (xp = x.parent) == null ? null : xp.parent;
                    }
                    if (xp != null) {
                        xp.red = false;
                        if (xpp != null) {
                            xpp.red = true;
                            root = rotateLeft(root, xpp);
                        }
                    }
                }
            }
        }
    }

    static <K, V> MyTreeNode<K, V> balanceDeletion(MyTreeNode<K, V> root,
                                                   MyTreeNode<K, V> x) {
        for (MyTreeNode<K, V> xp, xpl, xpr; ; ) {
            if (x == null || x == root)
                return root;
            else if ((xp = x.parent) == null) {
                x.red = false;
                return x;
            } else if (x.red) {
                x.red = false;
                return root;
            } else if ((xpl = xp.left) == x) {
                if ((xpr = xp.right) != null && xpr.red) {
                    xpr.red = false;
                    xp.red = true;
                    root = rotateLeft(root, xp);
                    xpr = (xp = x.parent) == null ? null : xp.right;
                }
                if (xpr == null)
                    x = xp;
                else {
                    MyTreeNode<K, V> sl = xpr.left, sr = xpr.right;
                    if ((sr == null || !sr.red) &&
                            (sl == null || !sl.red)) {
                        xpr.red = true;
                        x = xp;
                    } else {
                        if (sr == null || !sr.red) {
                            if (sl != null)
                                sl.red = false;
                            xpr.red = true;
                            root = rotateRight(root, xpr);
                            xpr = (xp = x.parent) == null ?
                                    null : xp.right;
                        }
                        if (xpr != null) {
                            xpr.red = (xp == null) ? false : xp.red;
                            if ((sr = xpr.right) != null)
                                sr.red = false;
                        }
                        if (xp != null) {
                            xp.red = false;
                            root = rotateLeft(root, xp);
                        }
                        x = root;
                    }
                }
            } else { // symmetric
                if (xpl != null && xpl.red) {
                    xpl.red = false;
                    xp.red = true;
                    root = rotateRight(root, xp);
                    xpl = (xp = x.parent) == null ? null : xp.left;
                }
                if (xpl == null)
                    x = xp;
                else {
                    MyTreeNode<K, V> sl = xpl.left, sr = xpl.right;
                    if ((sl == null || !sl.red) &&
                            (sr == null || !sr.red)) {
                        xpl.red = true;
                        x = xp;
                    } else {
                        if (sl == null || !sl.red) {
                            if (sr != null)
                                sr.red = false;
                            xpl.red = true;
                            root = rotateLeft(root, xpl);
                            xpl = (xp = x.parent) == null ?
                                    null : xp.left;
                        }
                        if (xpl != null) {
                            xpl.red = (xp == null) ? false : xp.red;
                            if ((sl = xpl.left) != null)
                                sl.red = false;
                        }
                        if (xp != null) {
                            xp.red = false;
                            root = rotateRight(root, xp);
                        }
                        x = root;
                    }
                }
            }
        }
    }

    /**
     * Recursive invariant check
     */
    static <K, V> boolean checkInvariants(MyTreeNode<K, V> t) {
        MyTreeNode<K, V> tp = t.parent, tl = t.left, tr = t.right,
                tb = t.prev, tn = (MyTreeNode<K, V>) t.next;
        if (tb != null && tb.next != t)
            return false;
        if (tn != null && tn.prev != t)
            return false;
        if (tp != null && t != tp.left && t != tp.right)
            return false;
        if (tl != null && (tl.parent != t || tl.hash > t.hash))
            return false;
        if (tr != null && (tr.parent != t || tr.hash < t.hash))
            return false;
        if (t.red && tl != null && tl.red && tr != null && tr.red)
            return false;
        if (tl != null && !checkInvariants(tl))
            return false;
        if (tr != null && !checkInvariants(tr))
            return false;
        return true;
    }
}
