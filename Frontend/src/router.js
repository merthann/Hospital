import { createRouter, createWebHistory } from 'vue-router';
import DoctorList from './components/DoctorList.vue';
import DoctorUpdate from './components/DoctorUpdate.vue';
import PatientList from './components/PatientList.vue';
import PatientUpdate from './components/PatientUpdate.vue';
import PoliclinicList from './components/PoliclinicList.vue';
import PoliclinicUpdate from './components/PoliclinicUpdate.vue';
import MedicineList from './components/MedicineList.vue';
import MedicineUpdate from './components/MedicineUpdate.vue';
import CreateMedicine from './components/CreateMedicine.vue';
import AssistantList from './components/AssistantList.vue';
import AssistantUpdate from './components/AssistantUpdate.vue';
import CreateDoctor from './components/CreateDoctor.vue';
import CreatePoliclinic from './components/CreatePoliclinic.vue';
import CreateAssistant from './components/CreateAssistant.vue';
import AppointmentList from './components/AppointmentList.vue';
import AppointmentUpdate from './components/AppointmentUpdate.vue';
import CreateAppointment from './components/CreateAppointment.vue';
import CreatePatient from './components/CreatePatient.vue';

const routes = [
  {
    path: '/CreatePatient',
    name: 'CreatePatient',
    component: CreatePatient
  },
  {
    path: '/CreateAppointment',
    name: 'CreateAppointment',
    component: CreateAppointment
  },
  {
    path: '/AppointmentList',
    name: 'AppointmentList',
    component: AppointmentList
  },
  {
    path: '/AppointmentUpdate',
    name: 'AppointmentUpdate',
    component: AppointmentUpdate
  },
  {
    path: '/DoctorList',
    name: 'DoctorList',
    component: DoctorList
  },
  { 
    path: '/CreateDoctor',
    name: 'CreateDoctor', 
    component: CreateDoctor 
  },
  {
    path: '/Doctor/update/:id',
    name: 'DoctorUpdate',
    component: DoctorUpdate,
    props: true
  },
  {
  path: '/PoliclinicList',
  name: 'PoliclinicList',
  component: PoliclinicList
},
{
  path: '/PoliclinicUpdate',
  name: 'PoliclinicUpdate',
  component: PoliclinicUpdate
},

{
  path: '/CreatePoliclinic',
  name: 'CreatePoliclinic',
  component: CreatePoliclinic
},
  {
    path: '/PatientList',
    name: 'PatientList',
    component: PatientList
  },
  {
    path: '/AssistantList',
    name: 'AssistantList',
    component: AssistantList
  },
  {
  path: '/CreateAssistant',
  name: 'CreateAssistant',
  component: CreateAssistant
  },
  {
    path: '/Assistant/update/:id',
    name: 'AssistantUpdate',
    component: AssistantUpdate,
    props: true
  },
  {
    path: '/Patient/update/:id',
    name: 'PatientUpdate',
    component: PatientUpdate,
    props: true
  },
  {
    path: '/MedicineList',
    name: 'MedicineList',
    component: MedicineList
  },
  {
    path: '/MedicineUpdate',
    name: 'MedicineUpdate',
    component: MedicineUpdate
  },
  
  {
    path: '/CreateMedicine',
    name: 'CreateMedicine',
    component: CreateMedicine,
    props: true
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;