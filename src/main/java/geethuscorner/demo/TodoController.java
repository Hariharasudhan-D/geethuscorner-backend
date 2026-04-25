package geethuscorner.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequestMapping("/todos")
@RestController
public class TodoController {
    @Autowired
    private TodoService todoService;

    @PostMapping
    ResponseEntity<Todo> create(@RequestBody Todo todo){
        return new ResponseEntity<>(todoService.createTodo(todo), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<Todo> getTodoById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(todoService.getTodoById(id),HttpStatus.OK);
        }catch(RuntimeException exception){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }


    @PutMapping
    ResponseEntity<Todo> updatetodo(@RequestBody Todo todo){
        return new ResponseEntity<>(todoService.updateTodo(todo), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable long id){
        todoService.deleteTodo(id);
    }




}
