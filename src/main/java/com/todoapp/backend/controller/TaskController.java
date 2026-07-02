package com.todoapp.backend.controller;

import com.todoapp.backend.dto.TaskDto;
import com.todoapp.backend.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.classfile.Opcode;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private  final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDto> saveTask(@RequestBody  TaskDto taskDto) {
    TaskDto savedTask= taskService.saveTask(taskDto);
    return ResponseEntity.ok().body(savedTask);
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTask(){
    List<TaskDto> tasks= taskService.getAllTasks();
    return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getAllTaskById( @PathVariable Long id){
        Optional<TaskDto> task = taskService.getTaskById(id);
        return task.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable long id, @RequestBody   TaskDto taskDto) {
        taskService.updateTask(id,taskDto);
        return ResponseEntity.ok().body(taskDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }
}

//
//Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Handler dispatch failed: java.lang.NoSuchMethodError: 'void org.springframework.web.method.ControllerAdviceBean.<init>(java.lang.Object)'] with root cause
//
//java.lang.NoSuchMethodError: 'void org.springframework.web.method.ControllerAdviceBean.<init>(java.lang.Object)'
//at org.springdoc.core.service.GenericResponseService.lambda$getGenericMapResponse$8(GenericResponseService.java:706) ~[springdoc-openapi-starter-common-2.6.0.jar:2.6.0]
//at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:196) ~[na:na]
//at java.base/java.util.Spliterators$ArraySpliterator.forEachRemaining(Spliterators.java:1024) ~[na:na]
//at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:570) ~[na:na]
//at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560) ~[na:na]
//at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:635) ~[na:na]
//at java.base/java.util.stream.AbstractPipeline.evaluateToArrayNode(AbstractPipeline.java:291) ~[na:na]
//at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:652) ~[na:na]
//at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:658) ~[na:na]
//at java.base/java.util.stream.ReferencePipeline.toList(ReferencePipeline.java:663) ~[na:na]
//at org.springdoc.core.service.GenericResponseService.getGenericMapResponse(GenericResponseService.java:708) ~[springdoc-openapi-starter-common-2.6.0.jar:2.6.0]
//at org.springdoc.core.service.GenericResponseService.build(GenericResponseService.java:247) ~[springdoc-openapi-starter-common-2.6.0.jar:2.6.0]
//at org.springdoc.api.AbstractOpenApiResource.calculatePath(AbstractOpenApiResource.java:499) ~[springdoc-openapi-starter-common-2.6.0.jar:2.6.0]
//at org.springdoc.api.AbstractOpenApiResource.calculatePath(AbstractOpenApiResource.java:676) ~[springdoc-openapi-starter-common-2.6.0.jar:2.6.0]
//at org.springdoc.webmvc.api.OpenApiResource.lambda$calculatePath$11(OpenApiResource.java:220) ~[springdoc-openapi-starter-webmvc-api-2.6.0.jar:2.6.0]
//at java.base/java.util.Optional.ifPresent(Optional.java:178) ~[na:na]
//at org.springdoc.webmvc.api.OpenApiResource.calculatePath(OpenApiResource.java:201) ~[springdoc-openapi-starter-webmvc-api-2.6.0.jar:2.6.0]
//at org.springdoc.webmvc.api.OpenApiResource.lambda$getPaths$2(OpenApiResource.java:171) ~[springdoc-openapi-starter-webmvc-api-2.6.0.jar:2.6.0]
//at java.base/java.util.Optional.ifPresent(Optional.java:178) ~[na:na]
//at org.springdoc.webmvc.api.OpenApiResource.getPaths(OpenApiResource.java:150) ~[springdoc-openapi-starter-webmvc-api-2.6.0.jar:2.6.0]
//at org.springdoc.api.AbstractOpenApiResource.getOpenApi(AbstractOpenApiResource.java:353) ~[springdoc-openapi-starter-common-2.6.0.jar:2.6.0]
//at org.springdoc.webmvc.api.OpenApiResource.openapiJson(OpenApiResource.java:125) ~[springdoc-openapi-starter-webmvc-api-2.6.0.jar:2.6.0]
//at org.springdoc.webmvc.api.OpenApiWebMvcResource.openapiJson(OpenApiWebMvcResource.java:114) ~[springdoc-openapi-starter-webmvc-api-2.6.0.jar:2.6.0]
//at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:104) ~[na:na]
//at java.base/java.lang.reflect.Method.invoke(Method.java:565) ~[na:na]
//at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:252) ~[spring-web-7.0.7.jar:7.0.7]
//at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:184) ~[spring-web-7.0.7.jar:7.0.7]
//at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:117) ~[spring-webmvc-7.0.7.jar:7.0.7]
//at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:934) ~[spring-webmvc-7.0.7.jar:7.0.7]
//at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:853) ~[spring-webmvc-7.0.7.jar:7.0.7]
//at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:86) ~[spring-webmvc-7.0.7.jar:7.0.7]
//at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:963) ~[spring-webmvc-7.0.7.jar:7.0.7]
//at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:866) ~[spring-webmvc-7.0.7.jar:7.0.7]
//at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1000) ~[spring-webmvc-7.0.7.jar:7.0.7]
//at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:892) ~[spring-webmvc-7.0.7.jar:7.0.7]
//at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:622) ~[tomcat-embed-core-11.0.21.jar:6.1]
//at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:874) ~[spring-webmvc-7.0.7.jar:7.0.7]
//at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:710) ~[tomcat-embed-core-11.0.21.jar:6.1]
//at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:128) ~[tomcat-embed-core-11.0.21.jar:11.0.21]
//at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53) ~[tomcat-embed-websocket-11.0.21.jar:11.0.21]
//at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:107) ~[tomcat-embed-core-11.0.21.jar:11.0.21]
//at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100) ~[spring-web-7.0.7.jar:7.0.7]
//at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-7.0.7.jar:7.0.7]
//at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:107) ~[tomcat-embed-core-11.0.21.jar:11.0.21]
//at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93) ~[spring-web-7.0.7.jar:7.0.7]
//at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-7.0.7.jar:7.0.7]
//at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:107) ~[tomcat-embed-core-11.0.21.jar:11.0.21]
//at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:199) ~[spring-web-7.0.7.jar:7.0.7]
//at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-7.0.7.jar:7.0.7]
//at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:107) ~[tomcat-embed-core-11.0.21.jar:11.0.21]
//at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:165) ~[tomcat-embed-core-11.0.21.jar:11.0.21]
//at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:77) ~[tomcat-embed-core-11.0.21.jar:11.0.21]
//at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:492) ~[tomcat-embed-core-11.0.21.jar:11.0.21]
//at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:113) ~[tomcat-embed-core-11.0.21.jar:11.0.21]
//at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:83) ~[tomcat-embed-core-11.0.21.jar:11.0.21]
//at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:72) ~[tomcat-embed-core-11.0.21.jar:11.0.21]
//at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:341) ~[tomcat-embed-core-11.0.21.jar:11.0.21]
//at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:397) ~[tomcat-embed-core-11.0.21.jar:11.0.21]
//at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63) ~[tomcat-embed-core-11.0.21.jar:11.0.21]
//at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:903) ~[tomcat-embed-core-11.0.21.jar:11.0.21]
//at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1801) ~[tomcat-embed-core-11.0.21.jar:11.0.21]
//at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:52) ~[tomcat-embed-core-11.0.21.jar:11.0.21]
//at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:946) ~[tomcat-embed-core-11.0.21.jar:11.0.21]
//at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:480) ~[tomcat-embed-core-11.0.21.jar:11.0.21]
//at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:57) ~[tomcat-embed-core-11.0.21.jar:11.0.21]
//at java.base/java.lang.Thread.run(Thread.java:1474) ~[na:na]