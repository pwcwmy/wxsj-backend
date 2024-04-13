<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="物种名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入物种名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属名录" prop="belongListId" width = “100”>
        <el-input
          v-model="queryParams.belongListId"
          placeholder="请输入物种所属名录"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="危险等级" prop="dangerLevel">
        <el-input
          v-model="queryParams.dangerLevel"
          placeholder="请输入危险等级"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="法条id" prop="lawId">
        <el-input
          v-model="queryParams.lawId"
          placeholder="请输入法条id"
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
          v-hasPermi="['oss:species:add']"
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
          v-hasPermi="['oss:species:edit']"
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
          v-hasPermi="['oss:species:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['oss:species:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="speciesList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="30" align="center" />
      <el-table-column label="序号" width="50" align="center" prop="id" />
      <el-table-column label="物种名"  align="center" prop="name" />
      <el-table-column label="物种所属名录" align="center" prop="belongListId" />
      <el-table-column label="简介" align="center" prop="speciesInfo" />
      <el-table-column label="危险等级" width="60" align="center" prop="dangerLevel" />
<!--      <el-table-column label="评级依据" align="center" prop="dangerLevelReason" />-->
<!--      <el-table-column label="措施" align="center" prop="measures" />-->
      <el-table-column label="法条序号" width="50" align="center" prop="lawId" />
      <el-table-column label="解决方案" align="center" prop="solution" />
      <el-table-column label="物种图片1" align="center" prop="picture1" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.picture1" :width="50" :height="50"/>
        </template>
      </el-table-column>
<!--      <el-table-column label="物种图片2" align="center" prop="picture2" width="100">-->
<!--        <template slot-scope="scope">-->
<!--          <image-preview :src="scope.row.picture2" :width="50" :height="50"/>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="物种图片3" align="center" prop="picture3" width="100">-->
<!--        <template slot-scope="scope">-->
<!--          <image-preview :src="scope.row.picture3" :width="50" :height="50"/>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="逻辑删除：0未删除，1已删除" align="center" prop="isDeleted">-->
<!--        <template slot-scope="scope">-->
<!--          <dict-tag :options="dict.type.sys_normal_disable" :value="scope.row.isDeleted"/>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['oss:species:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['oss:species:remove']"
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

    <!-- 添加或修改物种数据库信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="物种名" prop="name">
          <el-input v-model="form.name" placeholder="请输入物种名" />
        </el-form-item>
        <el-form-item label="物种所属名录" prop="belongListId">
          <el-input v-model="form.belongListId" placeholder="请输入物种所属的名录id" />
        </el-form-item>
        <el-form-item label="物种简介" prop="speciesInfo">
          <el-input v-model="form.speciesInfo" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="危险等级" prop="dangerLevel">
          <el-input v-model="form.dangerLevel" placeholder="请输入危险等级" />
        </el-form-item>
        <el-form-item label="评级依据" prop="dangerLevelReason">
          <el-input v-model="form.dangerLevelReason" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="措施" prop="measures">
          <el-input v-model="form.measures" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="法条id" prop="lawId">
          <el-input v-model="form.lawId" placeholder="请输入法条id" />
        </el-form-item>
        <el-form-item label="解决方案" prop="solution">
          <el-input v-model="form.solution" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="物种图片1" prop="picture1">
          <image-upload v-model="form.picture1"/>
        </el-form-item>
        <el-form-item label="物种图片2" prop="picture2">
          <image-upload v-model="form.picture2"/>
        </el-form-item>
        <el-form-item label="物种图片3" prop="picture3">
          <image-upload v-model="form.picture3"/>
        </el-form-item>
        <el-form-item label="逻辑删除：0未删除，1已删除" prop="isDeleted">
          <el-input v-model="form.isDeleted" placeholder="请输入逻辑删除：0未删除，1已删除" />
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
import { listSpecies, getSpecies, delSpecies, addSpecies, updateSpecies } from "@/api/oss/species";

export default {
  name: "Species",
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
      // 物种数据库信息表格数据
      speciesList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        belongListId: null,
        speciesInfo: null,
        dangerLevel: null,
        dangerLevelReason: null,
        measures: null,
        lawId: null,
        solution: null,
        picture1: null,
        picture2: null,
        picture3: null,
        isDeleted: null
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
    /** 查询物种数据库信息列表 */
    getList() {
      this.loading = true;
      listSpecies(this.queryParams).then(response => {
        this.speciesList = response.rows;
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
        name: null,
        belongListId: null,
        speciesInfo: null,
        dangerLevel: null,
        dangerLevelReason: null,
        measures: null,
        lawId: null,
        solution: null,
        picture1: null,
        picture2: null,
        picture3: null,
        isDeleted: null
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
      this.title = "添加物种数据库信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getSpecies(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改物种数据库信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateSpecies(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSpecies(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除物种数据库信息编号为"' + ids + '"的数据项？').then(function() {
        return delSpecies(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('oss/species/export', {
        ...this.queryParams
      }, `species_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
