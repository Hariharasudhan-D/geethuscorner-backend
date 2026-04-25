package geethuscorner.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public Todo createTodo(Todo todo){
        return todoRepository.save(todo);
    }

   public Todo getTodoById(Long id){
        return todoRepository.findById(id).orElseThrow(()->new RuntimeException("todo not found"));

   }
    public Todo updateTodo(Todo todo){
        return todoRepository.save(todo);
    }
    public void deleteTodo(long id){
         todoRepository.delete(getTodoById(id));
    }
}
