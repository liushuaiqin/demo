<template>
  <div class="login-container">

    <div class="login-box">
      <div class="title-container">
        <h3 class="title">ようこそ！</h3>
      </div>
          <el-form ref="ruleFormRef" :model="ruleForm" :rules="rules" class="login-form" :size="formSize" status-icon>
            <el-form-item prop="name">
              <el-input v-model="ruleForm.name" placeholder="ユーザー名" prefix-icon="User"/>
            </el-form-item>
            <el-form-item prop="password">
              <el-input v-model="ruleForm.password" show-password placeholder="パスワード" prefix-icon="Lock"/>
            </el-form-item>
            <el-form-item class="btn-group">
              <el-button type="primary" @click="submitForm(ruleFormRef)" class="login-btn">
                ログイン
              </el-button>
              <el-button @click="goRegister" class="register-btn">登録</el-button>
            </el-form-item>
          </el-form>
      </div>
  </div>
</template>
<script setup>
import { reactive, ref } from 'vue'
import { login } from '@/api/logininfo'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'

const formSize = ref('default')
const ruleFormRef = ref()
const ruleForm = reactive({
  name: '',
  password: '',
})

const router = useRouter();

const rules = reactive({
  name: [
    { required: true, message: '正しいユーザー名を入力してください', trigger: 'blur' },
    { min: 3, max: 10, message: '正しいユーザー名を入力してください', trigger: 'blur' },
  ],
  password: [
    { required: true, message: 'パスワードを入力してください', trigger: 'blur' },
    { min: 6, max: 20, message: '正しいパスワードを入力してください', trigger: 'blur' },
    //{pattern: /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]+$/, message: '密码必须包含大写字母、小写字母、数字和特殊字符', trigger: 'blur'}
  ],
})

const submitForm = async (formEl) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      login( ruleForm ).then(res => {
        if (res.code === 1) {
          // console.log('date:', res)
          const name = res.data?.name
          ElMessage.success(`${name}さんようこそ！`)
          router.push('/user')
        }
        else {
          ElMessage.error(res.msg)
        }
      })
    } else {
      console.log('error submit!', fields)
    }
  })
}

const goRegister = () => {
  router.push('/register');
};

</script>

<style scoped>

.login-container {
  width: 100vw;
  height: 100vh;
  overflow: hidden;
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
}

.login-box {
  width: 400px;
  padding: 40px;
  background-color: rgba(255, 255, 255, 0.8);
  border-radius: 10px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  text-align: center;
  position: relative;
  z-index: 1;
}

.title-container {
  margin-bottom: 30px;
}

.title {
  color: #2c3e50;
  font-size: 26px;
  font-weight: bold;
}

.login-form {
  padding: 0 30px;
}

.login-btn,
.register-btn {
  width: 48%;
  margin-top: 15px;
}

</style>