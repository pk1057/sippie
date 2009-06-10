/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package converter.res;

import java.util.Stack;

/**
 *
 * @author christian
 */
public class UndoRedoList<E> {
    
    private Stack<E> undo = null;
    private Stack<E> redo = null;
    
    public UndoRedoList() {
        undo = new Stack<E>();
        redo = new Stack<E>();
    }
    
    public E pushTask(E task) {
        redo.clear();
        return undo.push(task);
    }
    
    public E makeUndo(E task) {
        redo.push(task);
        return undo.pop();
    }
    
    public E makeRedo (E task) {
        undo.push(task);
        return redo.pop();
    }
    
    public boolean hasMoreUndoTasks() {
        return !undo.empty();
    }
    
    public boolean hasMoreRedoTasks() {
        return !redo.empty();
    }
}
