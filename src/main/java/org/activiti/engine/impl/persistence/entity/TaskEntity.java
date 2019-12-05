package org.activiti.engine.impl.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.delegate.event.impl.ActivitiEventBuilder;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.db.BulkDeleteable;
import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.db.HasRevision;
import org.activiti.engine.impl.db.PersistentObject;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.task.DelegationState;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;

import java.io.Serializable;
import java.util.*;
@Getter
@Setter
public class TaskEntity extends VariableScopeImpl implements Task, DelegateTask, Serializable,
        PersistentObject, HasRevision, BulkDeleteable {
    private static final long serialVersionUID = 7370313234405090806L;

    public static final String DELETE_REASON_COMPLETED = "complete";
    public static final String DELETE_REASON_DELETED = "deleted";

    protected int revision;

    protected String owner;
    protected String assignee;
    protected String initialAssignee;
    protected DelegationState delegationState;

    protected String parentTaskId;

    protected String name;
    protected String localizedName;
    protected String description;
    protected String localizedDescription;
    protected int priority = DEFAULT_PRIORITY;
    protected Date createTime; // The time when the task has been created
    protected Date dueDate;
    protected int suspensionState = SuspensionState.ACTIVE.getStateCode();
    protected String category;

    protected boolean isIdentityLinksInitialized = false;
    protected List<IdentityLinkEntity> taskIdentityLinkEntities = new ArrayList<IdentityLinkEntity>();

    protected String executionId;
    protected ExecutionEntity execution;

    protected String processInstanceId;
    protected ExecutionEntity processInstance;

    protected String processDefinitionId;

    protected TaskDefinition taskDefinition;
    protected String taskDefinitionKey;
    protected String formKey;

    protected boolean isDeleted;

    protected String eventName;

    protected String tenantId = ProcessEngineConfiguration.NO_TENANT_ID;

    protected List<VariableInstanceEntity> queryVariables;

    protected boolean forcedUpdate;

    public TaskEntity(){
    }

    public TaskEntity(String taskId){
        this.id = taskId;
    }

    /**
     * 创建新的任务
     * @param execution
     * @return
     */
    public static TaskEntity createAndInsert(ActivityExecution execution){
        TaskEntity taskEntity = create(Context.getProcessEngineConfiguration().getClock().getCurrentTime());
        taskEntity.insert((ExecutionEntity) execution);
        return taskEntity;
    }

    /**
     * 任务创建
     * @param createTime
     * @return
     */
    public static TaskEntity create(Date createTime){
        TaskEntity task = new TaskEntity();
        task.isIdentityLinksInitialized = true;
        task.createTime = createTime;
        return task;
    }

    public void insert(ExecutionEntity execution){
        CommandContext commandContext = Context.getCommandContext();
        DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
        dbSqlSession.insert(this);

        if (execution != null && execution.getTenantId() != null){
            setTenantId(execution.getTenantId());
        }

        if (execution != null){
            execution.addTask(this);
        }

        commandContext.getHistoryManager().recordTaskCreated(this,execution);

        if(commandContext.getProcessEngineConfiguration().getEventDispatcher().isEnabled()){
            commandContext.getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(
                    ActivitiEventBuilder.createEntityEvent(ActivitiEventType.ENTITY_CREATED,this));
            commandContext.getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(
                    ActivitiEventBuilder.createEntityEvent(ActivitiEventType.ENTITY_INITIALIZED,this));
        }
    }























    @Override
    public DelegateExecution getExecution() {
        return null;
    }

    @Override
    public String getEventName() {
        return null;
    }

    @Override
    public void addCandidateUser(String s) {

    }

    @Override
    public void addCandidateUsers(Collection<String> collection) {

    }

    @Override
    public void addCandidateGroup(String s) {

    }

    @Override
    public void addCandidateGroups(Collection<String> collection) {

    }

    @Override
    public void addUserIdentityLink(String s, String s1) {

    }

    @Override
    public void addGroupIdentityLink(String s, String s1) {

    }

    @Override
    public void deleteCandidateUser(String s) {

    }

    @Override
    public void deleteCandidateGroup(String s) {

    }

    @Override
    public void deleteUserIdentityLink(String s, String s1) {

    }

    @Override
    public void deleteGroupIdentityLink(String s, String s1) {

    }

    @Override
    public Set<IdentityLink> getCandidates() {
        return null;
    }

    @Override
    public void setRevision(int i) {

    }

    @Override
    public int getRevision() {
        return 0;
    }

    @Override
    public int getRevisionNext() {
        return 0;
    }

    @Override
    public Object getPersistentState() {
        return null;
    }

    @Override
    protected List<VariableInstanceEntity> loadVariableInstances() {
        return null;
    }

    @Override
    protected VariableScopeImpl getParentVariableScope() {
        return null;
    }

    @Override
    protected void initializeVariableInstanceBackPointer(VariableInstanceEntity variableInstanceEntity) {

    }

    @Override
    protected VariableInstanceEntity getSpecificVariable(String s) {
        return null;
    }

    @Override
    protected List<VariableInstanceEntity> getSpecificVariables(Collection<String> collection) {
        return null;
    }

    @Override
    public void setName(String s) {

    }

    @Override
    public void setLocalizedName(String s) {

    }

    @Override
    public void setDescription(String s) {

    }

    @Override
    public void setLocalizedDescription(String s) {

    }

    @Override
    public void setPriority(int i) {

    }

    @Override
    public void setOwner(String s) {

    }

    @Override
    public void setAssignee(String s) {

    }

    @Override
    public DelegationState getDelegationState() {
        return null;
    }

    @Override
    public void setDelegationState(DelegationState delegationState) {

    }

    @Override
    public void setDueDate(Date date) {

    }

    @Override
    public void setCategory(String s) {

    }

    @Override
    public void delegate(String s) {

    }

    @Override
    public void setParentTaskId(String s) {

    }

    @Override
    public void setTenantId(String s) {

    }

    @Override
    public void setFormKey(String s) {

    }

    @Override
    public boolean isSuspended() {
        return false;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public String getOwner() {
        return null;
    }

    @Override
    public String getAssignee() {
        return null;
    }

    @Override
    public String getProcessInstanceId() {
        return null;
    }

    @Override
    public String getExecutionId() {
        return null;
    }

    @Override
    public String getProcessDefinitionId() {
        return null;
    }

    @Override
    public Date getCreateTime() {
        return null;
    }

    @Override
    public String getTaskDefinitionKey() {
        return null;
    }

    @Override
    public Date getDueDate() {
        return null;
    }

    @Override
    public String getCategory() {
        return null;
    }

    @Override
    public String getParentTaskId() {
        return null;
    }

    @Override
    public String getTenantId() {
        return null;
    }

    @Override
    public String getFormKey() {
        return null;
    }

    @Override
    public Map<String, Object> getTaskLocalVariables() {
        return null;
    }

    @Override
    public Map<String, Object> getProcessVariables() {
        return null;
    }
}
