<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="关键词一" prop="key1">
        <el-input
          v-model="queryParams.key1"
          placeholder="请输入关键词一"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关键词二" prop="key2">
        <el-input
          v-model="queryParams.key2"
          placeholder="请输入关键词二"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关键词三" prop="key3">
        <el-input
          v-model="queryParams.key3"
          placeholder="请输入关键词三"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关键词四" prop="key4">
        <el-input
          v-model="queryParams.key4"
          placeholder="请输入关键词四"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关键词五" prop="key5">
        <el-input
          v-model="queryParams.key5"
          placeholder="请输入关键词五"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['wxsj:law:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['wxsj:law:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['wxsj:law:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['wxsj:law:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="lawList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="id" />
      <el-table-column label="分类" align="center" prop="classification" />
      <el-table-column label="关键词一" align="center" prop="key1" />
<!--      <el-table-column label="关键词二" align="center" prop="key2" />-->
<!--      <el-table-column label="关键词三" align="center" prop="key3" />-->
<!--      <el-table-column label="关键词四" align="center" prop="key4" />-->
<!--      <el-table-column label="关键词五" align="center" prop="key5" />-->
      <el-table-column label="名称" align="center" prop="clause" />
      <el-table-column label="内容" align="center" prop="content" />
      <el-table-column label="建议" align="center" prop="advice" />
<!--      <el-table-column label="0未删除，1已删除" align="center" prop="isDeleted" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['wxsj:law:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['wxsj:law:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改法律知识图谱对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="分类" prop="classification">
          <el-input v-model="form.classification" placeholder="请输入分类" />
        </el-form-item>
<!--        <el-form-item label="处方类型" prop="prescriptionType">-->
<!--          <el-select v-model="form.prescriptionType" placeholder="请选择处方类型">-->
<!--            <el-option-->
<!--              v-for="dict in dict.type.pill_drug_ptype"-->
<!--              :key="dict.value"-->
<!--              :label="dict.label"-->
<!--              :value="dict.value"-->
<!--            ></el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->
        <el-form-item label="关键词一" prop="key1">
          <el-input v-model="form.key1" placeholder="请输入优先级第一的关键词" />
        </el-form-item>
        <el-form-item label="关键词二" prop="key2">
          <el-input v-model="form.key2" placeholder="请输入优先级第二的关键词" />
        </el-form-item>
        <el-form-item label="关键词三" prop="key3">
          <el-input v-model="form.key3" placeholder="请输入优先级第三的关键词" />
        </el-form-item>
        <el-form-item label="关键词四" prop="key4">
          <el-input v-model="form.key4" placeholder="请输入优先级第四的关键词" />
        </el-form-item>
        <el-form-item label="关键词五" prop="key5">
          <el-input v-model="form.key5" placeholder="请输入优先级第五的关键词" />
        </el-form-item>
        <el-form-item label="法条名称" prop="clause">
          <el-input v-model="form.clause" placeholder="请输入法条名称" />
        </el-form-item>
        <el-form-item label="法律内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="建议" prop="advice">
          <el-input v-model="form.advice" placeholder="请输入建议" />
        </el-form-item>
        <el-form-item label="是否禁用" prop="status">
          <el-radio-group v-model="form.isDeleted">
            <el-radio
              v-for="dict in dict.type.sys_normal_disable"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listLaw, getLaw, delLaw, addLaw, updateLaw } from "@/api/wxsj/law";

export default {
  name: "Law",
  dicts: ['sys_normal_disable'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 法律知识图谱表格数据
      lawList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        classification: null,
        key1: null,
        key2: null,
        key3: null,
        key4: null,
        key5: null,
        clause: null,
        content: null,
        advice: null,
        isDeleted: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询法律知识图谱列表 */
    getList() {
      this.loading = true;
      listLaw(this.queryParams).then(response => {
        console.log(response);
        this.lawList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        classification: null,
        key1: null,
        key2: null,
        key3: null,
        key4: null,
        key5: null,
        clause: null,
        content: null,
        advice: null,
        isDeleted: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加法律知识图谱";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getLaw(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改法律知识图谱";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateLaw(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addLaw(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除法律知识图谱编号为"' + ids + '"的数据项？').then(function() {
        return delLaw(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('law/law/export', {
        ...this.queryParams
      }, `law_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
